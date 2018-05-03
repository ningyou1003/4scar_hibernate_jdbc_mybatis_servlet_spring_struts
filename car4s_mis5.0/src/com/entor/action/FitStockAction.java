package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.IFitStockDao;
import com.entor.entity.FitStock;
/**
 * 配件库存操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class FitStockAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private FitStock fitStock;
	@Autowired
	private IFitStockDao fitStockDao;

	public String list(){
		List<FitStock> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && fitStock != null) {
			param.put("brand", fitStock.getBrand());
			param.put("fitName", fitStock.getFitName());
			param.put("type", fitStock.getType());
		}
		if(getPage()==null){
		  setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(fitStockDao.select(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = fitStockDao.select(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}

	public FitStock getFitStock() {
		return fitStock;
	}

	public void setFitStock(FitStock fitStock) {
		this.fitStock = fitStock;
	}
}
