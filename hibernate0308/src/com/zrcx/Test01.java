package com.zrcx;

import com.zrcx.dao.DictDAO;
import com.zrcx.entity.Dict;

public class Test01 {

	public void select01(){
		DictDAO dao = new DictDAO();
		Dict d = dao.findById(1L);
		System.out.println(d.getDictName());
	}
}
