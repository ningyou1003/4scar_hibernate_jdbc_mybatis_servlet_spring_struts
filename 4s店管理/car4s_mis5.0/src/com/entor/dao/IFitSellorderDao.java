package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.FitSellorder;

public interface IFitSellorderDao {

	public List<FitSellorder> select(Map<String, Object> param);

	public FitSellorder getObjById(long id);

	public int delete(long id);

	public int update(FitSellorder obj);

	public int add(FitSellorder obj);

}