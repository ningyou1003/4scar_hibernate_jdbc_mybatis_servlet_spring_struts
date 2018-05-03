package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.Customer;

public interface ICustomerDao {

	public List<Customer> select(Map<String, Object> param);

	public Customer getObjById(long id);

	public int delete(long id);

	public int update(Customer obj);

	public int add(Customer obj);

}