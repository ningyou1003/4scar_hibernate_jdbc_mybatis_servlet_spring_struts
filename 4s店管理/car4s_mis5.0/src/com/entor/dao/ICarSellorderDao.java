package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.CarSellorder;

public interface ICarSellorderDao {

	public List<CarSellorder> select(Map<String, Object> param);

	public CarSellorder getObjById(long id);

	public int delete(long id);

	public int update(CarSellorder obj);

	public int add(CarSellorder obj);
	
	public int updateStock(long id);

}