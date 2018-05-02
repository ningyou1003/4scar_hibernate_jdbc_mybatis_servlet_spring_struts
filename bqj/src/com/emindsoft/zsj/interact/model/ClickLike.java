package com.emindsoft.zsj.interact.model;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;

@TableBind(tableName = "i_clicklike", pkName = "id")
public class ClickLike extends BaseModel<ClickLike> {

	public static ClickLike dao = new ClickLike();
	public static String table = "i_clicklike";
	
	public ClickLike findByPostUserId(String postId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
