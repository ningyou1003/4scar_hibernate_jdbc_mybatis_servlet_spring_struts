package com.entor.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entor.common.DateUtil;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.ICarSellorderDao;
import com.entor.dao.ICarStockDao;
import com.entor.entity.CarSellorder;
import com.entor.entity.CarStock;
import com.entor.entity.mapper.CarSellorderMapper;
@Repository
public class CarSellorderDao extends BaseDao implements ICarSellorderDao {
	@Autowired
	private ICarStockDao carStockDao;
	@Override
	public List<CarSellorder> select(Map<String,Object> param) {
		List<CarSellorder> list = null;
		try {
			String sql = "select a.*,b.brand,b.series,c.name custname " +
					" from T_4S_CAR_SELLORDER a,T_4S_CAR b,T_4S_CUSTOMER c " +
					" where a.car_id=b.id(+) and a.CUSTOMER_ID=c.id and a.del_flag=1 ";
			sql += this.apdStr(param, "brand", "", true);
			sql += this.apdStr(param, "series", "", true);
			sql += this.apdStr(param, "color", "", true);
			sql += this.apdStr(param, "salesMan", "",true);
			sql += this.apdNum(param, "customerId", "0");
			sql += this.apdNum(param, "sellState", "0");
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new CarSellorderMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public CarSellorder getObjById(long id){
		CarSellorder obj = null;
		try {
			String sql = "select a.*,b.brand,b.series,c.name custname " +
					" from T_4S_CAR_SELLORDER a,T_4S_CAR b,T_4S_CUSTOMER c " +
					" where a.car_id=b.id(+) and a.CUSTOMER_ID=c.id and a.del_flag=1 "+
					" and a.id=" + id;
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql,new CarSellorderMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return obj;
	}
	
	@Override
	public int delete(long id){  
		int result = 0;
		try {
			String sql = "update T_4S_CAR_SELLORDER set del_flag=0 where id="+id;
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	@Override
	public int update(CarSellorder obj){
		int result = 0;
		try {
			String sql = "update T_4S_CAR_SELLORDER set id=id ";
			if(isApd(obj, "count", "0")){
				sql += ",count =" + obj.getCount();
			}
			if(isApd(obj, "carId", "0")){
				sql += ",car_id =" + obj.getCarId();
			}
			if(isApd(obj, "sellPrice", "0.0")){
				sql += ",sell_price=" + obj.getSellPrice();
			}
			if(isApd(obj, "total", "0.0")){
				sql += ",total=" + obj.getTotal();
			}
			if(isApd(obj, "outState", "0")){
				sql += ",out_state =" + obj.getOutState();
			}
			if(obj.getOutDate()!=null){
				sql += ",out_date =to_date('"
			+ DateUtil.toStr2(obj.getOutDate()) +"'" +
					",'yyyy-mm-dd hh24:mi:ss')";
			}
			if(isApd(obj, "salesman", "")){
				sql += ",salesman='" + obj.getSalesman()+"'";
			}
			if(isApd(obj, "remark", "")){
				sql += ",remark='" + obj.getRemark()+"'";
			}
			sql += " where id= " + obj.getId(); 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int add(CarSellorder obj){
		int result = 0;
		try {
			String sql = "insert into T_4S_CAR_SELLORDER " +
              "(id,car_id,customer_id,sell_price,count,total," +
              " out_date,out_state,sell_date,remark,salesman)" +
              "values (SEQ_T_4S_CAR_SELLORDER.nextval," 
              + obj.getCarId()+","+obj.getCustomerId()+","+obj.getSellPrice()
              +","+obj.getCount()+","+obj.getTotal()+",null,1,sysdate,'"
              +obj.getRemark()+"','" + obj.getSalesman() +"')"; 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//提车操作(-1库存不足，0失败，1成功)
	@Override
	public int updateStock(long id) {
		int result = 0;
		// 根据ID查询销售单
		CarSellorder cars = this.getObjById(id);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("carId", cars.getCarId());
		List<CarStock> list = carStockDao.select(param);
		if (list.size() > 0) {
			CarStock cs = list.get(0);
			if(cars.getCount() > cs.getCount()){
				return -1;//库存不足
			}
			cs.setCount(cs.getCount()-cars.getCount());
			cs.setRemark("");
			carStockDao.update(cs);
		} else {
			return -1;
		}
		cars.setOutDate(new Date());
		cars.setOutState(2);
		result = this.update(cars);
		return result;
	}
}
