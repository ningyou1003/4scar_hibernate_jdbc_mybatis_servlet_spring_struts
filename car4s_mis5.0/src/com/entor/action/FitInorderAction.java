package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.IFitInorderDao;
import com.entor.entity.FitInorder;
/**
 * 配件进货单操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class FitInorderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private FitInorder fitInorder;
	@Autowired
	private IFitInorderDao fitInorderDao;

	public String list(){
		List<FitInorder> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && fitInorder != null) {
			param.put("inState", fitInorder.getInState());
			param.put("brand", fitInorder.getBrand());
			param.put("supplierId", fitInorder.getSupplierId());
			param.put("fitName", fitInorder.getFitName());
		}
		if(getPage()==null){
		  setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(fitInorderDao.select(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = fitInorderDao.select(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}
	public String select(){
		List<FitInorder> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && fitInorder !=null) {
			param.put("inState", fitInorder.getInState());
			param.put("brand", fitInorder.getBrand());
			param.put("supplierId", fitInorder.getSupplierId());
			param.put("fitName", fitInorder.getFitName());
		}
		if(getPage()==null){
			setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(fitInorderDao.select(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = fitInorderDao.select(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "select";
	}
	public String add(){
		//计算总价
		fitInorder.setTotal(1F*fitInorder.getCount()*fitInorder.getInPrice());
		int i = fitInorderDao.add(fitInorder);
		if(i > 0){
		  this.setMsg("新增成功!");	
		}else{
		  this.setMsg("新增失败!");
		}
		fitInorder = null;
		return list();
	}
	public String update(){
		// 初始化页面
		if (!this.isCommit()) {
			fitInorder = fitInorderDao.getObjById(fitInorder.getId());
			return "update";
		}
		fitInorder.setTotal(1F*fitInorder.getCount()*fitInorder.getInPrice());
		int i = fitInorderDao.update(fitInorder);
		if (i > 0) {
			this.setMsg("更新成功!");
		} else {
			this.setMsg("更新失败!");
		}
		return list();
	}
	
	public String delete(){
		int i = fitInorderDao.delete(fitInorder.getId());
		if(i > 0){
		  this.setMsg("删除成功!");	
		}else{
		  this.setMsg("删除失败!");
		}
		return list();
	}
	//车入库存
	public String ruKu(){
		int i = fitInorderDao.pickUp(fitInorder.getId());
		if(i > 0){
			this.setMsg("入库成功!");	
		}else{
			this.setMsg("入库失败!");
		}
		return list();
	}
	public FitInorder getFitInorder() {
		return fitInorder;
	}
	public void setFitInorder(FitInorder fitInorder) {
		this.fitInorder = fitInorder;
	}
}
