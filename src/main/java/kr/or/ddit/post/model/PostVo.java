package kr.or.ddit.post.model;

import java.util.Date;

public class PostVo {
	private String post_num;
	private String post_title;
	private String post_con;
	private Date post_dt;
	private String del;
	private String gn;
	private String userid;
	private String parent_num;
	private String board_num;
	private String level;
	
	public String getPost_num() {
		return post_num;
	}
	public void setPost_num(String post_num) {
		this.post_num = post_num;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_con() {
		return post_con;
	}
	public void setPost_con(String post_con) {
		this.post_con = post_con;
	}
	public Date getPost_dt() {
		return post_dt;
	}
	public void setPost_dt(Date post_dt) {
		this.post_dt = post_dt;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getGn() {
		return gn;
	}
	public void setGn(String gn) {
		this.gn = gn;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getParent_num() {
		return parent_num;
	}
	public void setParent_num(String parent_num) {
		this.parent_num = parent_num;
	}
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "PostVo [post_num=" + post_num + ", post_title=" + post_title
				+ ", post_con=" + post_con + ", post_dt=" + post_dt + ", del="
				+ del + ", gn=" + gn + ", userid=" + userid + ", parent_num="
				+ parent_num + ", board_num=" + board_num + ", level=" + level
				+ "]";
	}
}
