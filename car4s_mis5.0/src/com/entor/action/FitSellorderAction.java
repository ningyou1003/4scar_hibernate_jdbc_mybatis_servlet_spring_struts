package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.IFitSellorderDao;
import com.entor.entity.FitSellorder;
/**
 * 配件销售单操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class FitSellorderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private FitSellorder fitSellorder;
	@Autowired
	private IFitSellorderDao fitSellorderDao;

	public String list(){
		List<FitSellorder> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && fitSellorder != null) {
			param.put("outState", fitSellorder.getOutState());
			param.put("brand", fitSellorder.getBrand());
			param.put("customerId", fitSellorder.getCustomerId());
			param.put("fitName", fitSellorder.getFitName());
			param.put("salesman", fitSellorder.getSalesman());
		}
		if(getPage()==null){
		  setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(fitSellorderDao.select(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = fitSellorderDao.select(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}
	public String add(){
		//计算总价
		fitSellorder.setTotal(1F*fitSellorder.getCount()*fitSellorder.getSellPrice());
		int i = fitSellorderDao.add(fitSellorder);
		if(i > 0){
		  this.setMsg("新增成功!");	
		}else{
		  this.setMsg("新增失败!");
		}
		fitSellorder = null;
		return list();
	}
	public String update(){
		// 初始化页面
		if (!this.isCommit()) {
			fitSellorder = fitSellorderDao.getObjById(fitSellorder.getId());
			return "update";
		}
		fitSellorder.setTotal(1F*fitSellorder.getCount()*fitSellorder.getSellPrice());
		int i = fitSellorderDao.update(fitSellorder);
		if (i > 0) {
			this.setMsg("更新成功!");
		} else {
			this.setMsg("更新失败!");
		}
		return list();
	}
	
	public String delete(){
		int i = fitSellorderDao.delete(fitSellorder.getId());
		if(i > 0){
		  this.setMsg("删除成功!");	
		}else{
		  this.setMsg("删除失败!");
		}
		return list();
	}
	public FitSellorder getFitSellorder() {
		return fitSellorder;
	}
	public void setFitSellorder(FitSellorder fitSellorder) {
		this.fitSellorder = fitSellorder;
	}
}
