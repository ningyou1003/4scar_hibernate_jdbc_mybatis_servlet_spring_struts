package com.emindsoft.zsj.interact.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.interact.model.Post;
import com.emindsoft.zsj.interact.model.Reply;
import com.jfinal.plugin.activerecord.Page;

/**
 * 
 * Created by ym on 16-9-26.
 *
 */
@ControllerKey("interact/reply")
public class ReplyCtrl extends AdminBaseController<Reply> {

	public ReplyCtrl(){
		this.modelClass = Reply.class;
	}
	
	/**
	 * 添加评论，同时评论对应的互动评论数 +1
	 */
	public void add(){
		Reply reply;
		try {
			reply = getModel();
			reply.set("id", Post.dao.getId());
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			reply.set("createTime", time);
			reply.set("isRead", 0);
			reply.save();
			String postId = reply.getStr("postId");
			Post post = Post.dao.findById(postId);
			if(post != null){
				post.set("hasreply", post.getInt("hasReply")+1);
				post.update();
				success();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过id 获取评论详细信息
	 */
	public void findById(){
		String id = getPara("id");
		Reply reply = Reply.dao.findById(id);
		success(reply);
	}
	
	/**
	 * 获取当前用户所有未读评论
	 */
	public void getUnReadReply(){
		String userId = getCurrentUserId();
		int n = Reply.dao.getUnReadReply(userId);
		success(n);
	}
	
	/**
	 * 根据互动信息id取它的对应评论信息
	 */
	public void getReplyByPostId(){
		String postId = getPara("postId");
		Page<Reply> rList = Reply.dao.findByPostId(getPageNo(), getPageSize(), postId);
		success(rList);
	}
	
	/**
	 * 根据用户id获取别人对他发布的互动所有的评论
	 */
	public void getReplyByUserId(){
		String userId = getPara("userId");
		Page<Reply> rList = Reply.dao.getByUserId(getPageNo(), getPageSize(), userId);
		success(rList);
	}
	
	/**
	 * 根据id删除评论, 同时评论对应的互动评论数 -1
	 */
	public void deleteById(){
		String id = getPara("id");
		Reply reply = Reply.dao.findById(id);
		if(reply != null){
			Post post = Post.dao.findById(reply.getStr("postid"));
			Boolean b = Reply.dao.deleteById(id);
			if(post != null){
				int i = post.getInt("hasReply");
				if(i>0){
					i -= 1;
					post.set("hasreply", i);
					post.update();
				}
				success(b);
			}
			
		}
		
	}
	
	/**
	 * 将互动状态标记为已读
	 */
	public void isRead(){
		String id = getPara("id");
		Reply reply = Reply.dao.findById(id);
		if(reply != null){
			reply.set("isread", 1);
			reply.update();
			success(reply.get("isread"));
		}
	}
}
