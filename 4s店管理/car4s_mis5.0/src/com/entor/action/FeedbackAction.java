package com.entor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entor.common.Page;
import com.entor.dao.IFeedbackDao;
import com.entor.entity.Feedback;
/**
 * 客户反馈操作Action
 * @author ZHQL
 */
@Controller
@Scope("prototype")
public class FeedbackAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private Feedback feedback;
	@Autowired
	private IFeedbackDao feedbackDao;

	public String list(){
		List<Feedback> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && feedback !=null) {
			param.put("title", feedback.getTitle());
			param.put("cname", feedback.getName());
		}
		if(getPage()==null){
		  setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(feedbackDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = feedbackDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "list";
	}
	public String select(){
		List<Feedback> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.isCommit() && feedback !=null) {
			param.put("title", feedback.getTitle());
			param.put("name", feedback.getName());
		}
		if(getPage()==null){
			setPage(new Page());	
		}
		//查询总记录数
		getPage().setTotalRows(feedbackDao.list(param).size());
		//把分页信息传到DAO
		param.put("page", getPage());
		list = feedbackDao.list(param);
		//把集合放到request对象属性中，传送到页面显示
		request.setAttribute("list", list);
		return "select";
	}
	public String add(){
		int i = feedbackDao.add(feedback);
		if(i > 0){
		  this.setMsg("新增成功!");	
		}else{
		  this.setMsg("新增失败!");
		}
		feedback = null;
		return list();
	}
	public String update(){
		// 初始化页面
		if (!this.isCommit()) {
			feedback = feedbackDao.getObjById(feedback.getId());
			return "update";
		}
		int i = feedbackDao.update(feedback);
		if (i > 0) {
			this.setMsg("更新成功!");
		} else {
			this.setMsg("更新失败!");
		}
		return list();
	}
	
	public String delete(){
		int i = feedbackDao.delete(feedback.getId());
		if(i > 0){
		  this.setMsg("删除成功!");	
		}else{
		  this.setMsg("删除失败!");
		}
		return list();
	}
	
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
}
