package com.emindsoft.zsj.cases.model;

import java.util.List;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.culture.model.SilhouetteYear;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "c_formulayear",pkName = "KeyId")
public class FormulaYear extends BaseModel<FormulaYear>{
	
	private static final long serialVersionUID = 6141966050068434752L;
	public static FormulaYear dao = new FormulaYear();
	public static String table = "c_formulayear";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<FormulaYear> page(int pageNo,int pageSize) {
		return this.paginate
				(pageNo, pageSize,
				"select * "," from " + table + " b where 1 = 1  order by b.region desc");
	}

	public List<FormulaYear> loadYearTree() {		
		return find("select * from c_formulayear ORDER BY region DESC");
	}
}
