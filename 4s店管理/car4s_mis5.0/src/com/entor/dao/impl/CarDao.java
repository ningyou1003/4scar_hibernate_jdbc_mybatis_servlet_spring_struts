package com.entor.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.ICarDao;
import com.entor.entity.Car;
import com.entor.entity.mapper.CarMapper;
/**
 * 整车操作Dao
 * @author ZHQL
 */
@Transactional
@Repository
public class CarDao extends BaseDao implements ICarDao{
	
	//列表信息查询
	@Override
	public List<Car> list(Map<String,Object> param) {
		
		List<Car> list = new ArrayList<Car>();
		try {
			String sql = "select * from t_4s_car where del_flag=1 ";
			sql += this.apdStr(param, "brand", "", true);
			sql += this.apdStr(param, "series", "", true);
			sql += this.apdStr(param, "color", "", true);
			sql += this.apdNum(param, "type", "0");
			sql += this.apdNum(param, "volume", "0");
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new CarMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	@Override
	public int add(Car obj){
		int result = 0;
		try {
			String sql = "insert into T_4S_CAR " +
              "(id, brand, series, type, volume, color, pro_place, price, " +
              "create_date, remark, pic_path, vender)" +
              "values (seq_4s_car.nextval, " +
              "'"+obj.getBrand()+"','"+obj.getSeries() 
              +"'," + obj.getType()+",'"+ obj.getVolume()+"','"
              + obj.getColor()+"','"+ obj.getProPlace()+"',"
              + obj.getPrice()+",sysdate,'"+ obj.getRemark()
              +"','"+ obj.getPicPath()+"','"+ obj.getVender()+"')"; 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	@Override
	public int delete(int id){
		int result = 0;
		try {
			String sql = "update t_4s_car set del_flag=1 where id="+id;
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	@Override
	public int update(Car obj){
		int result = 0;
		try {
			String sql = "update t_4s_car set id=id ";
			if(isApd(obj, "brand", "")){
				sql += ",brand ='" + obj.getBrand() + "'";
			}
			if(isApd(obj, "series", "")){
				sql += ",series='" + obj.getSeries() + "'";
			}
			if(isApd(obj, "type", "0")){
				sql += ",type=" + obj.getType();
			}
			if(isApd(obj, "vender", "0")){
				sql += ",vender='" + obj.getVender()+"'";
			}
			if(isApd(obj, "volume", "")){
				sql += ",volume='" + obj.getVolume() + "'";
			}
			if(isApd(obj, "color", "")){
				sql += ",color='" + obj.getColor() + "'";
			}
			if(isApd(obj, "proPlace", "")){
				sql += ",pro_place='" + obj.getProPlace() + "'";
			}
			if(isApd(obj, "price", "0.0")){
				sql += ",price=" + obj.getPrice();
			}
			if(isApd(obj, "remark", "")){
				sql += ",remark=" + obj.getRemark();
			}
			if(isApd(obj, "picPath", "")){
				sql += ",pic_path='" + obj.getPicPath() + "'";
			}
			sql += " where id= " + obj.getId(); 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据ID查询
	 * @param list
	 * @return
	 */
	@Override
	public Car getObjById(int id){
		Car obj = null;
		try {
			String sql = "select * from t_4s_car where id=" + id; 
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql,new CarMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return obj;
	}

}
