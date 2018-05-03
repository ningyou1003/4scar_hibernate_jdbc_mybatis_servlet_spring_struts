package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.FitStock;

public interface IFitStockDao {

	public List<FitStock> select(Map<String, Object> param);
}