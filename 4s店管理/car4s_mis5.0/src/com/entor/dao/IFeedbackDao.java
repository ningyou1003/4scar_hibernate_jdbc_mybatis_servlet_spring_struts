package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.Feedback;

public interface IFeedbackDao {

	public List<Feedback> list(Map<String, Object> param);

	public Feedback getObjById(long id);

	public int delete(long id);

	public int update(Feedback obj);

	public int add(Feedback obj);

}