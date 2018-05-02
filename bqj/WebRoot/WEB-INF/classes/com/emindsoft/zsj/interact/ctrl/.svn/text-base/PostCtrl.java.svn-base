package com.emindsoft.zsj.interact.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.interact.model.Post;
import com.emindsoft.zsj.interact.model.Reply;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

/**
 * 
 * Created by ym on 16-9-26.
 *
 */
@ControllerKey("interact/post")
public class PostCtrl extends AdminBaseController<Post> {

	public PostCtrl(){
		this.modelClass = Post.class;
	}
	
	/**
	 * 发起互动话题
	 */
	public void add(){
		Post post;
		try {
			post = getModel();
			post.set("id", Post.dao.getId());
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			post.set("createTime", time);
			post.save();
			success();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id删除互动, 并且删除互动相关的所有评论
	 */
	public void deleteById(){
		String id = getPara("id");
		Boolean b = Post.dao.deleteById(id);
		Reply.dao.deleteByPostId(id);
		success(b);
	}
	
	/**
	 * 取所有的互动信息（按最新时间排序）i_post表信息
	 */
	public void postList(){
		Page<Post> pList = Post.dao.findAll(getPageNo(), getPageSize());
		success(pList);
	}
	
	/**
	 * 根据 id 获取互动详细信息
	 */
	public void postDetail(){
		String id = getPara("id");
		Post post = Post.dao.findById(id);
		success(post);
	}
	/**
	 * 根据用户id获取他的所有互动
	 */
	public void getPostByUserId(){
		String userId = getPara("userId");
		Page<Post> pList = Post.dao.findByUserId(getPageNo(), getPageSize(), userId);
		success(pList);
	}
	
	/**
	 * 获取评论最多的10条互动
	 */
	public void getMostReplyPost(){
		List<Post> pList = Post.dao.getMostReplyPost();
		success(pList);
	}
	
	/**
	 * 点赞+1
	 * @throws Exception 
	 */
	@Before(Tx.class)
	public void addHasLike() throws Exception{
		try {
			String id = getPara("id");
			Post post = Post.dao.findById(id);
			int hasLike = post.getInt("haslike");
			if(post != null){
				hasLike += 1;
				post.set("haslike", hasLike);
				post.update();
				success(hasLike);
			}
		} catch (Exception e) {
			error(002);
			throw e;
		}
	}
	
	/**
	 * 点赞 -1
	 * @throws Exception
	 */
	@Before(Tx.class)
	public void minusHaslike() throws Exception{
		try {
			String id = getPara("id");
			Post post = Post.dao.findById(id);
			if(post != null){
				int hasLike = post.getInt("haslike");
				if(hasLike>0){
					hasLike -= 1;
					post.set("haslike", hasLike);
					post.update();
					success(hasLike);
				}
			}
			
		} catch (Exception e) {
			error(002);
			throw e;
		}
	}
}
