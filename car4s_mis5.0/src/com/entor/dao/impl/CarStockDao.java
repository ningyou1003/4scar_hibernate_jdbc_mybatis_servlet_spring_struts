package com.entor.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.ICarStockDao;
import com.entor.entity.CarStock;
import com.entor.entity.mapper.CarStockMapper;
@Repository
public class CarStockDao extends BaseDao implements ICarStockDao {
	
	@Override
	public List<CarStock> select(Map<String,Object> param) {
		List<CarStock> list = null;
		try {
			String sql = "select a.*,b.brand,b.series,b.color,b.volume,b.type" +
					" from T_4S_CAR_STOCK a,T_4S_CAR b " +
					" where a.car_id=b.id ";
			sql += this.apdStr(param, "brand", "", true);
			sql += this.apdStr(param, "series", "", true);
			sql += this.apdStr(param, "volume", "", true);
			sql += this.apdNum(param, "type", "0");
			sql += this.apdNum(param, "carId", "0");
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new CarStockMapper());
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
	public int add(CarStock obj){
		int result = 0;
		try {
			String sql = "insert into T_4S_CAR_STOCK " +
              "(id, car_id, count, remark) " +
              "values (seq_4s_car_stock.nextval,"
              +obj.getCarId()+","+obj.getCount()
              +",'"+ obj.getRemark()+"')"; 
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
	public int update(CarStock obj){
		int result = 0;
		try {
			String sql = "update T_4S_CAR_STOCK set id=id ";
			if(isApd(obj, "count", "0")){
				sql += ",count=" + obj.getCount();
			}
			sql += " where id= " + obj.getId(); 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
