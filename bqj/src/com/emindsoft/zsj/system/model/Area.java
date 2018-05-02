package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.system.vo.AreaSelectVO;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@TableBind(tableName = "" + Area.table + "", pkName = "RegionCode")
@TableBind(tableName = "s_area", pkName = "id")
public class Area extends BaseModel<Area> {
	public static Area dao = new Area();
	public static String table = "s_area";

	/**
	 * 用于获取区域及其下属区域的区域代码最大值, 方便查询 比如广西为45000000, 则其区域代码范围为 45000000-45999999,
	 * 即把尾部0换为9即可 特例为根节点中国10000000, 其截止范围为99999999
	 * 
	 * @param regionCode
	 *            区域代码
	 * @return 区域代码最大值
	 */
	public static String maxRegionCode(String regionCode) {
		if (regionCode == null || regionCode.length() == 0) {
			return regionCode;
		}

		if (regionCode.equals("10000000")) {
			return "99999999";
		}

		String str = StringUtils.reverse(regionCode);
		Pattern pattern = Pattern.compile("[1-9]");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			str = str.substring(str.indexOf(matcher.group()));
			str = StringUtils.leftPad(str, regionCode.length(), "9");
			str = StringUtils.reverse(str);
		}

		return str;
	}

	/**
	 * 分页
	 * 
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页显示的记录条数
	 * @param seachCode
	 *            要查询的代码
	 * @return
	 */
	public Page<Area> page(int pageNo, int pageSize, String seachCode) {
		if (StringUtils.isEmpty(seachCode)) {
			return this.paginate(pageNo, pageSize, "select *", "FROM " + table
					+ " WHERE IsAvailable=0 ORDER BY RegionCode");
		} else {
			return this.paginate(pageNo, pageSize, "select *", "FROM " + table
					+ " WHERE RegionCode like '" + seachCode
					+ "%' AND IsAvailable=0 ORDER BY RegionCode");
		}

	}

	/**
	 * 加载区域列表
	 * 
	 * @param regioncode
	 *            区域代码
	 * @return
	 */
	public List<Area> loadArea(String regioncode, String currentUserId) {

		User u = User.dao.findById(currentUserId);
		if (StringUtils.isEmpty(regioncode)) {
			return find("select * from " + Area.table + " where regioncode = '"
					+ u.getStr("RegionId")
					+ "' and IsAvailable=0 order by regioncode");
		} else {
			return find(
					"select * from " + Area.table + " where parentcode = ? and IsAvailable=0 order by regioncode",
					regioncode);
		}
	}

	public List<Area> loadActArea(String regioncode) {

		if (StringUtils.isEmpty(regioncode)) {
			return find("select * from " + Area.table + " where  IsAvailable=0 order by regioncode");
		} else {
			return find(
					"select * from " + Area.table + " where parentcode = ? and IsAvailable=0 order by regioncode",
					regioncode);
		}
	}

	/**
	 * 根据登录用户的区域加载区域列表
	 * 
	 * @param regioncode
	 *            区域代码
	 * @param currentUserId
	 *            登录用户ID
	 * @return
	 */
	public List<Area> loadAreaByUserGegionId(String regioncode,
			String userRegionId) {
		boolean regionCodeIsEmpty = StringUtils.isEmpty(regioncode);
		if (regionCodeIsEmpty) {
			String sql = "SELECT IF(a.num>0, 'false', 'true') isLeaf , a.* FROM (SELECT *,COUNT(*) num FROM " + Area.table + " WHERE regioncode=?) a WHERE a.isavailable=0 order by a.regioncode";
			return find(sql ,userRegionId);
		} else {
			String sql = "SELECT if(d.num>0,'false', 'true') isLeaf, d.* FROM (" +
					"SELECT (SELECT COUNT(*) FROM " + Area.table + " b WHERE a.RegionCode=b.ParentCode)as num," +
					"(SELECT c.region FROM " + Area.table + " c WHERE c.RegionCode=a.ParentCode) AS parent, a.* " +
					"FROM " + Area.table + " a WHERE a.ParentCode =? ) d WHERE d.isavailable=0 order by d.regioncode";//order by d.regioncode
			return find(sql, regioncode);
		}
	}

	/**
	 * 获取给定区域的所有下属区域
	 * 
	 * @param areaList
	 * @param parentCode
	 * @return
	 */
	public List<Area> getChildList(List<Area> areaList, String regionCode) {
//		Area area = Area.dao.findFirst("SELECT * FROM " + Area.table + " WHERE RegionCode='"+regionCode+"'");
//		areaList.add(area);
		List<Area> aList = Area.dao.find("SELECT * FROM " + Area.table + " WHERE ParentCode='"+regionCode+"'");
		if(aList.size()>0){
			for(Area a : aList){
				String regioncode = a.getStr("regioncode");
				getChildList(areaList, regioncode);
			}
		}
		return areaList;
	}

	/**
	 * 获取指定区域及其所管辖的所有子区域
	 * 
	 * @param regionCode
	 * @return
	 */
	public List<Area> getChildrenList(String regionCode) {
		List<Area> areaList = new ArrayList<Area>();
		areaList = getChildList(areaList, regionCode);
		return areaList;
	}

	/**
	 * 获取指定区域编码及其所管辖子区域编码
	 * 
	 * @param regionCode
	 * @return
	 */
	public List<String> getRegionCodeList(String regionCode) {
		List<Area> areaList = new ArrayList<Area>();
		areaList = getChildList(areaList, regionCode);
		List<String> regionCodeList = new ArrayList<String>();
		for (Area area : areaList) {
			regionCodeList.add(area.getStr("regioncode"));
		}
		return regionCodeList;
	}
	
	/**
	 * 获取指定区域编码及其所管辖子区域编码
	 * 
	 * @param regionCode
	 * @return
	 */
	
	public List<String> getChildrenRegionCode(String regionCode) {
		List<Area> areaList = dao.find("select * from s_area where ParentCode='"+regionCode+"'");
		if (areaList==null) {
			return null;
		}
		List<String> regionCodeList = new ArrayList<String>();
		for (Area area : areaList) {
			regionCodeList.add(area.getStr("regioncode"));
		}
		return regionCodeList;
	}

	/**
	 * 删除选中区域,即把isAvailalbe设置位1，而不是真正的删除
	 * 
	 * @param regioncodes
	 *            选中区域代码
	 * @return
	 */
	public boolean deleteByRegioncode(String regioncode) {
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE ");
		sql.append(table);
		sql.append(" SET IsAvailable=1 WHERE RegionCode='" + regioncode + "'");
		int i = Db.update(sql.toString());
		return i > 0;
	}

	public Area findByRegion(String regioncode) {
		String sql = "select s.*,(select region from " + Area.table + " where RegionCode = s.ParentCode) as parent from " + Area.table + " s where RegionCode = ? and IsAvailable = 0";
		return findFirst(sql, regioncode);
	}

	/**
	 * 获取区域码，用于模糊搜索子区域的截取码
	 * 
	 * @param regioncode
	 * @return
	 */
	public static String getSeachCode(String regioncode) {
		String seachcode = null;

		if (!StringUtils.isEmpty(regioncode) && regioncode.length() == 2) {
			seachcode = regioncode.substring(0, 2);
		} else if (!StringUtils.isEmpty(regioncode) && regioncode.length() == 4) {
			seachcode = regioncode.substring(0, 4);
		} else {
			seachcode = regioncode;
		}
		return seachcode;
	}

	public List<Area> loadRoleTreeByUserRegionId(String regioncode,
			String currentUserId) {
		User u = User.dao.findById(currentUserId);
		return find(
				"select * from (select region as dataname,ParentCode as parentid,RegionCode as id,length(RegionCode) as orderkey "
						+ "from " + Area.table + " where IsAvailable=0  and ( ParentCode like '"
						+ getSeachCode(u.getStr("RegionId"))
						+ "%' or RegionCode = ? )"
						+ "UNION "
						+ "select Name as dataname,regioncode as parentid,keyid as id,length(keyid) as orderkey "
						+ "from " + Role.table + " where regioncode like '"
						+ getSeachCode(u.getStr("RegionId"))
						+ "%') a order by a.orderkey desc,a.id",
				u.getStr("RegionId"));
	}

	/**
	 * 分页
	 * 
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页显示的记录条数
	 * @param asVO
	 *            查询条件
	 * @param currentUserId
	 *            当前用户ｉｄ
	 * @return
	 */
	public Page<Area> page(int pageNo, int pageSize, AreaSelectVO asVO,
			String currentUserId,String areaname,int isDemonstration) {
		User u = User.dao.findById(currentUserId);
		String sql = "select * ";
		String sqlExpandSelect = "FROM " + table + " WHERE 1=1 ";
		boolean b=true;
		if (areaname!=null || isDemonstration>=0) {
//			System.out.println(123411);
			if (areaname!=null && areaname.length()>0) {
				sqlExpandSelect+=" and region like '%"+areaname+"%' ";
				b=false;
			}
			if (isDemonstration!=2) {
				if (isDemonstration==1) {
					sqlExpandSelect+=" and demonstration =1" ;
				}
				if (isDemonstration==0) {
					sqlExpandSelect+=" and (demonstration =0 or demonstration is null) " ;
				}
			}
			
		}

		String currentUserRegionId = u.getStr("regionId");

		List<String> paramList = new ArrayList<String>();

		
		
		String region = asVO.getRegion();
		String regioncode = asVO.getRegioncode();
		String parentcode = asVO.getParentcode();
		
		if(parentcode!=null && parentcode.equals("allDemonstration")){
			sqlExpandSelect+=" ORDER BY OperLevel";
			return this.paginate(pageNo, pageSize, sql, sqlExpandSelect);
		}
		

		// 依次判断查询功能的各个参数值是否为空
		if (parentcode != null) {
			currentUserRegionId = parentcode;
		}
		
		if (isDemonstration!=3) {
			if(!currentUserRegionId.equals("450000000000")){
				
				long minRegionId=Long.parseLong(currentUserRegionId);
				
				Area a=Area.dao.findFirst("select * from "+table+" where RegionCode='"+currentUserRegionId+"'");
				
				String sql2="select * from "+table+" where cast(RegionCode as SIGNED INTEGER)>"+minRegionId+" and OperLevel="+a.getInt("OperLevel")+" order by cast(RegionCode as SIGNED INTEGER)";
				a=Area.dao.findFirst(sql2);
				long maxRegionId=0;
				if (a==null) {
					 maxRegionId=minRegionId;
					 int n=0;
					 for(int i=1;i<15;i++){
						 if (maxRegionId%10==0) {
							 maxRegionId=maxRegionId/10;
						}else {
							n=i-1;
							break;
						}
					 }
					 maxRegionId++;
					 for(int j=0;j<n;j++){
						 maxRegionId=maxRegionId*10;
					 }
				}else {
					 maxRegionId=Long.parseLong(a.getStr("RegionCode"));
				}
				
				sqlExpandSelect += " and (cast(RegionCode as SIGNED INTEGER)>"+minRegionId;
				sqlExpandSelect += " and cast(RegionCode as SIGNED INTEGER)<"+maxRegionId+")";
				
				
				}
		}else {
			if(currentUserRegionId.equals("450000000000")){
				sqlExpandSelect += " and (parentCode=? or parentCode='100000000000') ";
			}else{
				sqlExpandSelect += " and (parentCode=? or regioncode=?)";
				paramList.add(currentUserRegionId);
			}
			paramList.add(currentUserRegionId);
		}
		
		
		

		if (region != null && b) {
			sqlExpandSelect += " AND region like ? ";
			
				paramList.add("%" + region + "%");
			
			
		}
		if (regioncode != null) {
			sqlExpandSelect += " AND regioncode like ? ";
			paramList.add("%" + regioncode + "%");
		}
		
		
		
		
		
		sqlExpandSelect += " AND IsAvailable=0 ORDER BY OperLevel";
		
//		System.out.println(sqlExpandSelect);

		if (paramList.size() != 0) {
			return this.paginate(pageNo, pageSize, sql, sqlExpandSelect,
					paramList.toArray());
		} else {
			return this.paginate(pageNo, pageSize, sql, sqlExpandSelect);
		}
	}

	public Area findByRegionCode(String regionCode) {
		return findFirst("SELECT * FROM " + Area.table + " WHERE regioncode='"
				+ regionCode + "'");
	}

	/**
	 * 查询根据父级区域码查询区域
	 * 
	 * @param parentcode
	 * @return
	 */
	public Area findByParentcode(String parentcode) {
		if (!StringUtils.isEmpty(parentcode)) {
			List<Area> areaList = find("SELECT * FROM " + Area.table + " WHERE ParentCode='"
					+ parentcode + "'");
			if (areaList.size() == 0) {
				Area area = Area.dao.findById(parentcode);
				parentcode = area.getStr("parentcode");
			}
		}
		return Area.dao.findById(parentcode);
	}

	public List<Area> loadAreaAndOrg(String regioncode, String userRegionId) {
		
		boolean regionCodeIsEmpty = StringUtils.isEmpty(regioncode);
		if (regionCodeIsEmpty) {
			String sql1 = "SELECT b.ID as ID,b.ParentCode as ParentCode,b.RegionCode as RegionCode,"
					+ "b.Region as Region,'' as orgNum,'isregion' AS type FROM " + Area.table + " b WHERE b.IsAvailable=0 and b.RegionCode=? UNION "
					+ "SELECT o.KeyID as ID,o.regioncode as ParentCode,o.KeyID as RegionCode,"
					+ "o.org_fullname AS  Region,o.orgNum as orgNum,'isorg' AS type FROM " + Org.table + " o WHERE o.isvailable='0' AND o.regioncode=? ORDER BY RegionCode ";
			return find(sql1, userRegionId, userRegionId);
		} else {
			String sql2 = "SELECT b.ID as ID,b.ParentCode as ParentCode,b.RegionCode as RegionCode,"
					+ "b.Region as Region,'' as orgNum,'isregion' AS type FROM " + Area.table + " b WHERE b.IsAvailable=0 and b.ParentCode=? UNION "
					+ "SELECT o.KeyID as ID,o.regioncode as ParentCode,o.KeyID as RegionCode,"
					+ "o.org_fullname AS  Region,o.orgNum as orgNum,'isorg' AS type FROM " + Org.table + " o WHERE o.isvailable='0' AND o.regioncode=? ORDER BY RegionCode";
			return find(sql2, regioncode, regioncode);
		}
	}
	

	/**
	 * 查找数据库中id值最大的记录
	 * 
	 * @return
	 */
	public Area findMaxId() {
		return findFirst("select max(id) max from " + table
				+ " where id <> 10000");
	}

	public List<Area> loadAllArea(String regioncode) {
		if (StringUtils.isEmpty(regioncode)) {
			String sql1 = "select s.* from " + Area.table + " s where s.IsAvailable=0 order by s.regioncode ";
			List<Area> alist = find(sql1);
			return alist;
		} else {
			String sql2 = "select s.*,(SELECT COUNT(*) FROM " + Area.table + " a WHERE a.ParentCode=s.RegionCode ) as isLeaf " +
					"from " + Area.table + " s where s.parentcode = ? and s.IsAvailable=0 order by s.regioncode ";
			List<Area> alist2 = find(sql2,regioncode);
			return alist2;
		}
	}
	
	public List<Area> loadAreaTreePickByUserRegionCode(String regioncode,String currentUserId,String userRegionCode) {
		if (StringUtils.isEmpty(regioncode)) {
			String sql1 = "select s.*,(SELECT COUNT(*) FROM " + Area.table + " a WHERE a.ParentCode=s.RegionCode ) as isLeaf " +
					"from " + Area.table + " s where s.IsAvailable=0 AND s.ParentCode=? OR s.RegionCode=? order by s.regioncode ";
			List<Area> alist = find(sql1,userRegionCode,userRegionCode);
			return alist;
		} else {
			String sql2 = "select s.*,(SELECT COUNT(*) FROM " + Area.table + " a WHERE a.ParentCode=s.RegionCode ) as isLeaf " +
					"from " + Area.table + " s where s.parentcode = ? and s.IsAvailable=0 order by s.regioncode ";
			List<Area> alist2 = find(sql2,regioncode);
			return alist2;
		}
	}
	
	public List<Area> loadAllParentArea(List<Area> pList, String regionId){
		Area area = new Area();
		area = findFirst("SELECT p.id,p.parentcode,p.regioncode,p.region,p.operlevel,p.demonstration FROM s_area s, s_area p WHERE s.parentcode=p.regioncode AND s.regioncode='"+regionId+"'");
		if(area!=null){
			
			pList.add(area);
			regionId = area.getStr("regioncode");
			loadAllParentArea(pList, regionId);
		}
		return pList;
	}
	
	public List<String> getAllParentAreaRegionId(String regionId){
		List<Area> list=new ArrayList<Area>();
		list=loadAllParentArea(list,regionId);
		List<String> plist=new ArrayList<String>();
		for(Area a:list){
			plist.add(a.getStr("regioncode"));
		}
		return plist;
		
	}
	
	/**
	 * 加载区域数据
	 * 增加年份返回
	 * 墙报树形目录数据来源
	 * @param year
	 * @param ParentCode
	 * @param regionCode
	 * @return
	 */
	public List<Area> loadRegionToSilhouette(String year, String ParentCode, String regionCode) {
		String sql = "SELECT region,regionCode,(" + year + ")year FROM " +  Area.table
				+ " d WHERE d.isavailable=0 and (d.ParentCode =? or d.regionCode = ?) order by d.regioncode";
		List<Area> list = Area.dao.find(sql,ParentCode,regionCode);
		
		return list;
	}
	
	/**
	 * 加载区域数据集
	 * 墙报区域数据来源
	 * @param ParentCode
	 * @param RegionCode
	 * @return
	 */
	public List<Area> loadRegionListToSilhouette(String ParentCode, String RegionCode) {
		return find("select region,regionCode,parentCode " +
				" FROM s_area WHERE  IsAvailable=0 AND (ParentCode = ? or RegionCode = ?) ORDER BY RegionCode",ParentCode,RegionCode);
	}
	
	/**
	 * 将给定的所有示范区域中，
	 * 示范区域的区域名后面加上‘（示范）’
	 * @param areaList
	 */
	public static void resetRegion(List<Area> areaList){
		for (Area a:areaList) {
			if (a.getInt("demonstration")!=null && a.getInt("demonstration")==1) {
				a.set("Region", a.getStr("Region")+"(示范)");
			}
		}
	}
	/**
	 * 判断给定区域，
	 * 是示范则区域名后面加‘（示范）’
	 * @param area
	 */
	public static void resetRegion(Area area){
		
			if (area.getInt("demonstration")!=null && area.getInt("demonstration")==1) {
				area.set("Region", area.getStr("Region")+"(示范)");
			}
		
	}
	
	public static Integer getAreaLevel(String regionCode){
		Area a=Area.dao.findByRegion(regionCode);
		if (a!=null) {
			return a.getInt("OperLevel");
		}else {
			return -1;
		}
		
	}
	
	public Map<String, String> getAllParentNames(List<String> parensCode){
		Collections.sort(parensCode);
		Map<String, String> parentname=new HashMap<String, String>();
		if (null==parensCode || parensCode.size()<2) {
			parentname.put("city", "");
			parentname.put("county", "");
			parentname.put("town", "");
			 
		}
		if (parensCode.size()==2) {
			parentname.put("city", dao.findByRegionCode(parensCode.get(1)).getStr("Region"));
			parentname.put("county", "");
			parentname.put("town", "");
			 
		}
		if (parensCode.size()==3) {
			parentname.put("city", dao.findByRegionCode(parensCode.get(1)).getStr("Region"));
			parentname.put("county", dao.findByRegionCode(parensCode.get(2)).getStr("Region"));
			parentname.put("town", "");
			 
		}
		if (parensCode.size()==4) {
			parentname.put("city", dao.findByRegionCode(parensCode.get(1)).getStr("Region"));
			parentname.put("county", dao.findByRegionCode(parensCode.get(2)).getStr("Region"));
			parentname.put("town", dao.findByRegionCode(parensCode.get(3)).getStr("Region"));
			 
		}
//		System.out.println(parensCode);
//		for(String s:parensCode){
//			System.out.println(s);
//		}
//		System.out.println(parentname.get("city"));
		return parentname;
	} 

}
