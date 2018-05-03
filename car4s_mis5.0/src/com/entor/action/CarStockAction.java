package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.ICarStockDao;
import com.entor.entity.CarStock;
/**
 * 汽车库存操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class CarStockAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private CarStock carStock;
	@Autowired
	private ICarStockDao carStockDao;

	public String list(){
		List<CarStock> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && carStock != null) {
			param.put("brand", carStock.getBrand());
			param.put("series", carStock.getSeries());
			param.put("type", carStock.getType());
			param.put("volume", carStock.getVolume());
		}
		if(getPage()==null){
		  setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(carStockDao.select(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = carStockDao.select(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}

	public CarStock getCarStock() {
		return carStock;
	}

	public void setCarStock(CarStock carStock) {
		this.carStock = carStock;
	}
}
