package kr.or.ddit.comments.model;

import java.util.Date;

public class CommentsVo {
	private String comment_num;
	private String comment_con;
	private Date comment_dt;
	private String del;
	private String userid;
	private String post_num;
	
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public String getComment_con() {
		return comment_con;
	}
	public void setComment_con(String comment_con) {
		this.comment_con = comment_con;
	}
	public Date getComment_dt() {
		return comment_dt;
	}
	public void setComment_dt(Date comment_DT) {
		this.comment_dt = comment_DT;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPost_num() {
		return post_num;
	}
	public void setPost_num(String post_num) {
		this.post_num = post_num;
	}
	
	@Override
	public String toString() {
		return "CommentsVo [comment_num=" + comment_num + ", comment_con="
				+ comment_con + ", comment_DT=" + comment_dt + ", del=" + del
				+ ", userid=" + userid + ", post_num=" + post_num + "]";
	}
}
