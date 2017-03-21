package cn.ljxt.model;

import java.sql.Date;


public class Invitation {

	private long id;
	
	private String title;//标题
	
	private String summary;//摘要
	
	private String author;//作者
	
	private Date date;//发帖日期

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	@Override
	public String toString() {
		return "Invitation [id=" + id + ", title=" + title + ", summary=" + summary + ", author=" + author + ", date="
				+ date + "]";
	}
	
	
}
