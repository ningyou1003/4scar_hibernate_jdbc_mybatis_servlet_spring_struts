package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.FileUpload;
import com.entor.common.Page;
import com.entor.dao.ICarDao;
import com.entor.entity.Car;
/**
 * 整车操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class CarAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private Car car;
	//文件上传工具
	private FileUpload fupload;
	@Autowired
	private ICarDao carDao;

	public String list(){
		List<Car> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && car !=null) {
			param.put("brand", car.getBrand());
			param.put("type", car.getType());
			param.put("volume", car.getVolume());
			param.put("color", car.getColor());
			param.put("series", car.getSeries());
			param.put("vender", car.getVender());
		}
		if(getPage()==null){
		  setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(carDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = carDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}
	
	public String select(){
		List<Car> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && car !=null) {
			param.put("brand", car.getBrand());
			param.put("type", car.getType());
			param.put("volume", car.getVolume());
			param.put("color", car.getColor());
			param.put("series", car.getSeries());
			param.put("vender", car.getVender());
		}
		if(getPage()==null){
			setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(carDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = carDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "select";
	}
	
	public String add(){
		//文件上传
		String fileName = fupload.upload();
		System.out.println(fileName + "-文件上传成功！");
		car.setPicPath(fileName);//上传图片的路径
		int i = carDao.add(car);
		if(i > 0){
		  this.setMsg("新增成功!");	
		}else{
		  this.setMsg("新增失败!");
		}
		car = null;
		return list();
	}
	
	public String update(){
		// 初始化页面
		if (!this.isCommit()) {
			car = carDao.getObjById(car.getId());
			return "update";
		}
		//如提交了新图片则更新
		if(fupload!=null){
		   String fileName = fupload.upload();
		   car.setPicPath(fileName);
		}
		int i = carDao.update(car);
		if (i > 0) {
			this.setMsg("更新成功!");
		} else {
			this.setMsg("更新失败!");
		}
		return list();
	}
	
	public String delete(){
		int i = carDao.delete(car.getId());
		if(i > 0){
		  this.setMsg("删除成功!");	
		}else{
		  this.setMsg("删除失败!");
		}
		return list();
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}

	public FileUpload getFupload() {
		return fupload;
	}

	public void setFupload(FileUpload fupload) {
		this.fupload = fupload;
	}
}
