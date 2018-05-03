package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.ICarSellorderDao;
import com.entor.entity.CarSellorder;
/**
 * 汽车销售单操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class CarSellorderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private CarSellorder carSellorder;
	@Autowired
	private ICarSellorderDao carSellorderDao;

	public String list(){
		List<CarSellorder> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && carSellorder != null) {
			param.put("outState", carSellorder.getOutState());
			param.put("brand", carSellorder.getBrand());
			param.put("series", carSellorder.getSeries());
			param.put("customerId", carSellorder.getCustomerId());
			param.put("salesman", carSellorder.getSalesman());
		}
		if(getPage()==null){
		  setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(carSellorderDao.select(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = carSellorderDao.select(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}
	public String add(){
		//计算总价
		carSellorder.setTotal(1F*carSellorder.getCount()*carSellorder.getSellPrice());
		int i = carSellorderDao.add(carSellorder);
		if(i > 0){
		  this.setMsg("新增成功!");	
		}else{
		  this.setMsg("新增失败!");
		}
		carSellorder = null;
		return list();
	}
	public String update(){
		// 初始化页面
		if (!this.isCommit()) {
			carSellorder = carSellorderDao.getObjById(carSellorder.getId());
			return "update";
		}
		//计算总价
		carSellorder.setTotal(1F*carSellorder.getCount()*carSellorder.getSellPrice());
		int i = carSellorderDao.update(carSellorder);
		if (i > 0) {
			this.setMsg("更新成功!");
		} else {
			this.setMsg("更新失败!");
		}
		return list();
	}
	
	public String delete(){
		int i = carSellorderDao.delete(carSellorder.getId());
		if(i > 0){
		  this.setMsg("删除成功!");	
		}else{
		  this.setMsg("删除失败!");
		}
		return list();
	}
	//提车
	public String tiHuo(){
		int i = carSellorderDao.updateStock(carSellorder.getId());
		if(i > 0){
			this.setMsg("提车成功!");	
		}else if(i==-1){
			this.setMsg("库存不足，提车失败");
		}else{
			this.setMsg("提车失败!");
		}
		return "list";
	}
	
	public CarSellorder getCarSellorder() {
		return carSellorder;
	}
	public void setCarSellorder(CarSellorder carSellorder) {
		this.carSellorder = carSellorder;
	}
}
