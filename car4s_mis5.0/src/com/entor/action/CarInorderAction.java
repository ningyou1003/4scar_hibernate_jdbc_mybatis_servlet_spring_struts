package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.ICarInorderDao;
import com.entor.entity.CarInorder;
/**
 * 汽车进货单操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class CarInorderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private CarInorder carInorder;
	@Autowired
	private ICarInorderDao carInorderDao;

	public String list(){
		List<CarInorder> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && carInorder != null) {
			param.put("inState", carInorder.getInState());
			param.put("brand", carInorder.getBrand());
			param.put("series", carInorder.getSeries());
			param.put("supplierId", carInorder.getSupplierId());
		}
		if(getPage()==null){
		  setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(carInorderDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = carInorderDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}
	public String select(){
		List<CarInorder> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && carInorder !=null) {
			param.put("inState", carInorder.getInState());
			param.put("brand", carInorder.getBrand());
			param.put("series", carInorder.getSeries());
			param.put("supplierId", carInorder.getSupplierId());
		}
		if(getPage()==null){
			setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(carInorderDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = carInorderDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "select";
	}
	public String add(){
		//计算总价
		carInorder.setTotal(1F*carInorder.getCount()*carInorder.getInPrice());
		int i = carInorderDao.add(carInorder);
		if(i > 0){
		  this.setMsg("新增成功!");	
		}else{
		  this.setMsg("新增失败!");
		}
		carInorder = null;
		return list();
	}
	public String update(){
		// 初始化页面
		if (!this.isCommit()) {
			carInorder = carInorderDao.getObjById(carInorder.getId());
			return "update";
		}
		//计算总价
		carInorder.setTotal(1F*carInorder.getCount()*carInorder.getInPrice());
		int i = carInorderDao.update(carInorder);
		if (i > 0) {
			this.setMsg("更新成功!");
		} else {
			this.setMsg("更新失败!");
		}
		return list();
	}
	
	public String delete(){
		int i = carInorderDao.delete(carInorder.getId());
		if(i > 0){
		  this.setMsg("删除成功!");	
		}else{
		  this.setMsg("删除失败!");
		}
		return list();
	}
	//车入库存
	public String ruKu(){
		int i = carInorderDao.updateStock(carInorder.getId());
		if(i > 0){
			this.setMsg("入库成功!");	
		}else{
			this.setMsg("入库失败!");
		}
		return "list";
	}
	public CarInorder getCarInorder() {
		return carInorder;
	}
	public void setCarInorder(CarInorder carInorder) {
		this.carInorder = carInorder;
	}
}
