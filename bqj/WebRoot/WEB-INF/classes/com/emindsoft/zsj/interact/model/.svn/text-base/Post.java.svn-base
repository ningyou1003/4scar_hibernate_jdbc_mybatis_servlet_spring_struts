package com.emindsoft.zsj.interact.model;

import java.util.List;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "i_post", pkName = "id")
public class Post extends BaseModel<Post> {

	public static Post dao = new Post();
	public static String table = "i_post";

	public Page<Post> findAll(int pageNo, int pageSize){
		String sql = "SELECT p.id, p.content, p.createtime, p.hasreply, p.haslike, u.RelaName username, p.userID " ;
		String sqlExpand = "FROM " + table + " p JOIN s_user u ON p.userID=u.KeyID ORDER BY p.createTime DESC";
		return this.paginate(pageNo, pageSize, sql, sqlExpand);
	}

	public Post findById(String id) {
		String sql = "SELECT p.id, p.content, p.createtime, p.hasreply, p.haslike, u.RelaName username, p.userID FROM "
				+ table
				+ " p JOIN s_user u ON p.userID=u.KeyID WHERE p.id='"
				+ id + "' ORDER BY p.createTime DESC";
		return findFirst(sql);
	}

	public Page<Post> findByUserId(int pageNO, int pageSize, String userId) {
		String sql = "SELECT p.id, p.content, p.createtime, p.hasreply, p.haslike, u.RelaName username, P.userID ";
		String sqlExpand = "FROM " + table + " p JOIN s_user u ON p.userID=u.KeyID WHERE p.userID='" + userId + "' ORDER BY p.createTime DESC";
		return this.paginate(pageNO, pageSize, sql, sqlExpand);
	}

	public List<Post> getMostReplyPost() {
		String sql = "SELECT p.id, p.content, p.createtime, p.hasreply, p.haslike, u.RelaName username, p.userID FROM "
				+ table
				+ " p JOIN s_user u ON p.userID=u.KeyID ORDER BY p.hasReply DESC LIMIT 10";
		return find(sql);
	}
}
