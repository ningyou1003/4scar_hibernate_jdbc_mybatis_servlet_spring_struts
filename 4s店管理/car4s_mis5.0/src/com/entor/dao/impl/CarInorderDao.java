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
import com.entor.dao.ICarInorderDao;
import com.entor.dao.ICarStockDao;
import com.entor.entity.CarInorder;
import com.entor.entity.CarStock;
import com.entor.entity.mapper.CarInorderMapper;
@Repository
public class CarInorderDao extends BaseDao implements ICarInorderDao {
	@Autowired
	private ICarStockDao carStockDao;
	@Override
	public List<CarInorder> list(Map<String,Object> param) {
		List<CarInorder> list = null;
		try {
			String sql = "select a.*,b.brand,b.series,c.name sname " +
					" from T_4S_CAR_INORDER a,T_4S_CAR b,T_4S_SUPPLIER c " +
					" where a.car_id=b.id and a.supplier_id=c.id and a.del_flag=1 ";
			sql += this.apdStr(param, "brand", "", true);
			sql += this.apdStr(param, "series", "", false);
			sql += this.apdStr(param, "color", "", true);
			sql += this.apdNum(param, "inState", "0");
			sql += this.apdNum(param, "supplierId", "0");
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new CarInorderMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public CarInorder getObjById(long id){
		CarInorder obj = null;
		try {
			String sql = "select a.*,b.brand,b.series,c.name sname from T_4S_CAR_INORDER a,T_4S_CAR b,T_4S_SUPPLIER c " +
					" where a.car_id=b.id(+) and a.supplier_id=c.id(+) and a.del_flag=1 " +
					" and a.id=" + id;
			System.out.println("sql:" + sql);
			obj = jdbctmp.queryForObject(sql,new CarInorderMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return obj;
	}
	@Override
	public int delete(long id){  
		int result = 0;
		try {
			String sql = "update T_4S_CAR_INORDER set del_flag=0 where id="+id;
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	@Override
	public int update(CarInorder obj){
		int result = 0;
		try {
			String sql = "update T_4S_CAR_INORDER set id=id ";
			if(isApd(obj, "count", "0")){
				sql += ",count =" + obj.getCount();
			}
			if(isApd(obj, "carId", "0")){
				sql += ",car_id =" + obj.getCarId();
			}
			if(isApd(obj, "inState", "0")){
				sql += ",in_state =" + obj.getInState();
			}
			if(obj.getInDate()!=null){
				sql += ",in_date =to_date('"
			+ DateUtil.toStr2(obj.getInDate()) +"'" +
					",'yyyy-mm-dd hh24:mi:ss')";
			}
			if(isApd(obj, "inPrice", "0.0")){
				sql += ",in_price=" + obj.getInPrice();
			}
			if(isApd(obj, "total", "0.0")){
				sql += ",total=" + obj.getTotal();
			}
			if(isApd(obj, "remark", "")){
				sql += ",remark=" + obj.getRemark();
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
	public int add(CarInorder obj){
		int result = 0;
		try {
			String sql = "insert into T_4S_CAR_INORDER " +
              "(id,car_id,supplier_id,in_price,count,total,in_date,in_state,create_date,remark)" +
              "values (SEQ_T_4S_CAR_INORDER.nextval," 
              + obj.getCarId()+","+obj.getSupplierId()+","+obj.getInPrice()
              +","+obj.getCount()+","+obj.getTotal()+",null,1,sysdate,'"
              +obj.getRemark()+"')"; 
			System.out.println("sql:" + sql);
			result = jdbctmp.update(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//整车入库
	@Override
	public int updateStock(long id){
		int result = 0;
		//根据ID查询进货单
		CarInorder cari = this.getObjById(id);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("carId", cari.getCarId());
		List<CarStock> list = carStockDao.select(param);
		if(list.size()==0){
			CarStock cs = new CarStock();
			cs.setCount(cari.getCount());
			cs.setCarId(cari.getCarId());
			cs.setRemark("新车入库");
			carStockDao.add(cs);
		}else{
			CarStock cs =list.get(0);
			cs.setCount(cari.getCount()+cs.getCount());
			cs.setRemark("添车入库");
			carStockDao.update(cs);
		}
		cari.setInDate(new Date());
		cari.setInState(2);		
		result = this.update(cari);
		return result;
	}
	
}
