package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.FitInorder;

public interface IFitInorderDao {

	public List<FitInorder> select(Map<String, Object> param);

	public FitInorder getObjById(long id);

	public int delete(long id);

	public int update(FitInorder obj);

	public int add(FitInorder obj);
	
	public int pickUp(long id);

}