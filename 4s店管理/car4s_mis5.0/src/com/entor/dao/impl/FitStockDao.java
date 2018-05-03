package com.entor.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entor.common.Page;
import com.entor.dao.BaseDao;
import com.entor.dao.IFitStockDao;
import com.entor.entity.FitStock;
import com.entor.entity.mapper.FitStockMapper;
@Transactional
@Repository
public class FitStockDao extends BaseDao implements IFitStockDao {
	
	@Override
	public List<FitStock> select(Map<String,Object> param) {
		List<FitStock> list = null;
		try {
			String sql = "select a.*,b.brand,b.name fname,b.type " +
					" from T_4S_FITTINGS_STOCK a,T_4S_FITTINGS b " +
					" where a.fittings_id=b.id(+) ";
			sql += this.apdStr(param, "brand", "", true);
			sql += this.apdStr(param, "fitName", "", true);
			sql += this.apdStr(param, "type", "", false);
			//分页对象
			if(null!=param && null!= param.get("page")){
				sql = this.getPageSql(sql,(Page)param.get("page"));
			}
			System.out.println("sql: " + sql);
			list = jdbctmp.query(sql,new FitStockMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
