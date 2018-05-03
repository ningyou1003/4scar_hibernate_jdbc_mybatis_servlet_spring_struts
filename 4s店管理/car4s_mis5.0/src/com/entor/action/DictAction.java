package com.entor.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.IDictDao;
import com.entor.entity.Dict;
/**
 * 字典操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class DictAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IDictDao dictDao;
	private Dict dict;
	/**
	 * 列表查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && dict != null) {//点击查询时
			// 查询参数
			param.put("dictName", dict.getDictName());
			param.put("key", dict.getKey());
			param.put("value", dict.getValue());
			param.put("useFlag", dict.getUseFlag());
		}
		//
		if(getPage()==null){
			this.setPage(new Page());
		}
		//查询总记录数
		getPage().setTotalRows(dictDao.list(param).size());
		//把分页对象传到Dao
		param.put("page",getPage());
		//查询列表并把数据集合放到request对象中，运到加页面显示
		request.setAttribute("list", dictDao.list(param));
		return "list";
	}
	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		dict.setCreateDate(new Date());
		int i = dictDao.add(dict);
		if(i>0){
			this.setMsg("新增操作成功");
		}else{
			this.setMsg("新增操作失败");
		}
		dict = null;
		return this.list();
	}
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(!this.isCommit()){//初始化页面
			dict = dictDao.getObjById(dict.getId());
			return "update";
		}
		int i = dictDao.update(dict);
		if(i>0){
			this.setMsg("更新操作成功");
		}else{
			this.setMsg("更新操作失败");
		}
		dict = null;
		return this.list();
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		int i = dictDao.delete(dict.getId());
		if(i>0){
			this.setMsg("删除操作成功");
		}else{
			this.setMsg("删除操作失败");
		}
		return this.list();
	}
	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}
}
