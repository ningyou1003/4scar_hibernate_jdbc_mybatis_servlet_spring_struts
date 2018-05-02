package com.emindsoft.zsj.interact.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.interact.model.ClickLike;
import com.emindsoft.zsj.interact.model.Post;

@ControllerKey("interact/clicklike")
public class ClickLikeCtrl extends AdminBaseController<ClickLike> {

	public ClickLikeCtrl(){
		this.modelClass = ClickLike.class;
	}
	
	public void add(){
		ClickLike clickLike;
		try {
			clickLike = getModel();
			String postId = clickLike.get("postID");
			String userId = clickLike.get("userID");
			ClickLike cl = ClickLike.dao.findByPostUserId(postId, userId);
			if(cl == null){
				Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=format.format(date);
				clickLike.set("createTime", time);
				clickLike.save();
				Post post = Post.dao.findById(postId);
				if(post != null){
					post.set("hasLike", Post.dao.getInt("hasLike")+1);
					post.update();
					success();
				}
	
			} else {
				error(002);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(){
		
	}
}
