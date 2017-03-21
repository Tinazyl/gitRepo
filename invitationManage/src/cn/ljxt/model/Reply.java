package cn.ljxt.model;

import java.sql.Date;

public class Reply {

	private long id;
	
	private String content;//回复内容
	
	private String author;//回帖作者
	
	private Date date;//回复日期
	
	private Invitation invitation;//对应的帖子

	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Invitation getInvitation() {
		return invitation;
	}



	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}



	@Override
	public String toString() {
		return "Reply [id=" + id + ", content=" + content + ", author=" + author + ", date=" + date + ", invitation="
				+ invitation + "]";
	}
	
	 
	
}
