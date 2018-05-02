package com.emindsoft.zsj.system.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.dreampie.tablebind.TableBind;

import com.alibaba.fastjson.JSON;
import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.vo.LeftMenuVO;
import com.jfinal.plugin.activerecord.Db;

/**
 * 权限模块
 * @author ym
 *
 */
@TableBind(tableName = "s_power", pkName = "KeyId")//@TableBind固定注解标签，对应数据库表s_powers，主键KeyId
public class Power extends BaseModel<Power> {

	public static Power dao = new Power();//new查询dao，一般都固定的语法
	public static String table = "s_power";//把对应的表名赋予给变量table，方便调用，如在sql拼接时候
	private List<Power> menuList;//定义一个Power类的List泛型对象

	/**
	 * 左侧菜单方法
	 * @param uid 用户id
	 * @return
	 */
	public LeftMenuVO getLeftMenuList(String uid) {
		String sql = "select p.* from " + Power.table + " p where p.keyId='0'" +
				" union " +
				"select p.* from " + Power.table + " p, " + RolePower.table + " rp, s_role_user ru " +
				"where ru.roleid=rp.objectId and rp.powerId=p.keyId and p.ISShow=0 and p.keyId<>'0' and ru.userid=? ";

		//TODO 暂时显示全部菜单
		sql = "select p.* from " + Power.table + " p where p.keyId='0' " +
				"union " +
				"select p.* from " + Power.table + " p " +
				"where p.ISShow=0 and p.keyId<>'0' and 'aaa'<>?";


		String expand = " order by ParentId, OrderId";
		User u = User.dao.findFirst("SELECT OperLevel FROM s_user u JOIN s_area a ON u.RegionId=a.RegionCode AND u.KeyID='"+uid+"'");
		if(u!=null){
			int OperLevel = u.getInt("OperLevel");
			if(OperLevel>=3){
				sql += " and KeyID LIKE '1201' OR keyid=12 OR KeyID LIKE '15%' OR KeyID LIKE '16%' OR KeyID LIKE '11%' OR KeyID=1 OR ParentID=1 OR KeyID=0 OR KeyID LIKE '8%' ";
			}
		}
		List<Power> list = find(sql+expand, uid);
		Map<String, LeftMenuVO> map = new LinkedHashMap<String, LeftMenuVO>();

		LeftMenuVO root = null;

		for(int i=0; i<list.size(); i++){
			Power p = list.get(i);
			String id = p.getStr("keyId");
			String parentId = p.getStr("parentId");

			LeftMenuVO leftMenuVO = new LeftMenuVO(p);
			map.put(id, leftMenuVO);

			if(i==0){
				root = leftMenuVO;
				continue;
			}

			if(map.containsKey(parentId)){
				map.get(parentId).getChild().add(leftMenuVO);
			}
		}

		return root;
	}
	
	public LeftMenuVO getLeftMenuList1(String regionId) {
		String sql = "";

		//TODO 暂时显示全部菜单
		sql = "select p.* from " + Power.table + " p where p.keyId='0' " +
				"union " +
				"select p.* from " + Power.table + " p " +
				"where p.ISShow=0 and p.keyId<>'0' ";


		String expand = " order by ParentId, OrderId";
		Area area = Area.dao.findByRegionCode(regionId);
		if(area != null){
			int OperLevel = area.getInt("OperLevel");
			if(OperLevel !=0 &&OperLevel < 3){
				sql += " and KeyID <>'1003' and KeyID <>'1004'";
			}
			
			if(OperLevel>=3){
				sql += " and KeyID LIKE '1201' OR keyid=12 OR KeyID LIKE '16%' OR KeyID LIKE '15%' OR KeyID LIKE '11%' OR KeyID=1 OR ParentID=1 OR KeyID=0 OR KeyID LIKE '8%' ";
			}
			
		}
		List<Power> list = find(sql+expand);
		Map<String, LeftMenuVO> map = new LinkedHashMap<String, LeftMenuVO>();

		LeftMenuVO root = null;

		for(int i=0; i<list.size(); i++){
			Power p = list.get(i);
			String id = p.getStr("keyId");
			String parentId = p.getStr("parentId");

			LeftMenuVO leftMenuVO = new LeftMenuVO(p);
			map.put(id, leftMenuVO);

			if(i==0){
				root = leftMenuVO;
				continue;
			}

			if(map.containsKey(parentId)){
				map.get(parentId).getChild().add(leftMenuVO);
			}
		}

		return root;
	}

	public List<Power> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Power> menuList) {
		this.menuList = menuList;
	}

	public List<Power> getTopMenuList(String uid) {
		String sql = "select * from " + table
				+ " where parentID = 0 and ISShow = 0 and keyID in " + getPowerFilterSql() + " order by OrderID,KeyID";
		List<Power> plist = find(sql, uid);
		return plist;
	}

	public String getPowerFilterSql() {
		String s = "(select powerID from " + RolePower.table + " where LookPower=1 and objectID in "
				+ "(select roleid from s_role_user where userid=?))";
		return s;
	}

	public static void restoreMenu(){
		try{
			String path = Power.class.getResource("/").getPath() + "power.json";
			if(!System.getProperties().getProperty("os.name").toLowerCase().startsWith("linux")){
				path = path.substring(1);
			}
			InputStreamReader isr=new InputStreamReader(new FileInputStream(path), "utf-8");
			BufferedReader reader = new BufferedReader(isr);
			String line;
			StringBuffer json=new StringBuffer();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			reader.close();
			Db.update("TRUNCATE TABLE " + table);
			List<Map> powerList = JSON.parseArray(json.toString(), Map.class);
			for(Map p : powerList){
				new Power().setAttrs(p).save();
//				System.out.println(p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void restorePowerPermission(){
		try{
			String path = Power.class.getResource("/").getPath() + "power_permission.json";
			if(!System.getProperties().getProperty("os.name").toLowerCase().startsWith("linux")){
				path = path.substring(1);
			}
			InputStreamReader isr=new InputStreamReader(new FileInputStream(path), "utf-8");
			BufferedReader reader = new BufferedReader(isr);
			String line;
			StringBuffer json=new StringBuffer();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			reader.close();
			Db.update("TRUNCATE TABLE s_power_permission");
			List<Map> list = JSON.parseArray(json.toString(), Map.class);
			for(Map p : list){
				Db.update("insert into s_power_permission values(?,?,?,?)",
					p.get("keyid"),
					p.get("powerid"),
					p.get("key"),
					p.get("name")
				);
//				System.out.println(p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
