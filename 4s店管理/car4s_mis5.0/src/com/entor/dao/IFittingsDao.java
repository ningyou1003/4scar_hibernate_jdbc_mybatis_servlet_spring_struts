package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.Fittings;

public interface IFittingsDao {

	public List<Fittings> list(Map<String, Object> param);

	public Fittings getObjById(long id);

	public int delete(long id);

	public int update(Fittings obj);

	public int add(Fittings obj);
}