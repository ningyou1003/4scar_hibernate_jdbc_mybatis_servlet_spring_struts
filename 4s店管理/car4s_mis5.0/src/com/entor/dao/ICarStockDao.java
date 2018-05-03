package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.CarStock;

public interface ICarStockDao {
	public List<CarStock> select(Map<String, Object> param);
	public int add(CarStock obj);
	public int update(CarStock obj);
}