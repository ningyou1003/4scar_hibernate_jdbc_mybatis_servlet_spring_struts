package com.emindsoft.zsj.interact.model;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "i_reply", pkName = "id")
public class Reply extends BaseModel<Reply> {

	public static Reply dao = new Reply();
	public static String table = "i_reply";

	public Page<Reply> findByPostId(int pageNO, int pageSize, String postId) {
		String sql = "SELECT r.id, r.postID, r.content, r.createTime, u.relaname username, r.userID, r.isRead ";
		String sqlExpand = "FROM " + table + " r JOIN s_user u ON r.userID=u.KeyID WHERE r.postID='" + postId + "' ORDER BY r.createTime DESC";
		return this.paginate(pageNO, pageSize, sql, sqlExpand);
	}

	public Page<Reply> getByUserId(int pageNo, int pageSize, String userId) {
		String sql = "SELECT r.id, r.postID, r.content, r.createTime, u.relaname username, r.userID, r.isRead, p.content pcontent, p.pusername, p.userid puserid " ;
		String sqlExpand = "FROM " + table + " r JOIN s_user u ON r.userID=u.KeyID JOIN (SELECT id, content, userid, relaname pusername FROM i_post, s_user WHERE userID=KeyID) p ON p.id=r.postID  WHERE r.postid IN(SELECT rp.id FROM i_post  rp WHERE rp.userid='"+userId+"')  ORDER BY r.createTime DESC";
		return this.paginate(pageNo, pageSize, sql, sqlExpand);
	}
	
	public int deleteByPostId(String postId){
		String sql = "DELETE FROM " + table + " WHERE postid='" + postId + "'";
		int n = Db.update(sql);
		return n;
	}

	public int getUnReadReply(String userId) {
		String sql = "SELECT COUNT(r.id) num FROM " + table + " r JOIN i_post p ON p.id=r.postid WHERE p.userid='" + userId +"' AND r.isread=0";
		Reply reply = findFirst(sql);
		return new Long(reply.getLong("num")).intValue();
	}

}
