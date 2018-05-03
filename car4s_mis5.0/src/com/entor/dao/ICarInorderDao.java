package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.CarInorder;

public interface ICarInorderDao {

	public List<CarInorder> list(Map<String, Object> param);

	public CarInorder getObjById(long id);

	public int delete(long id);

	public int update(CarInorder obj);

	public int add(CarInorder obj);
	
	public int updateStock(long id);

}