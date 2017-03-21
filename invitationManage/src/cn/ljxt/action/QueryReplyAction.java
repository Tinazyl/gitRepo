package cn.ljxt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.ws.resources.HttpserverMessages;

import cn.ljxt.model.Invitation;
import cn.ljxt.model.Reply;
import cn.ljxt.service.InvitationService;

public class QueryReplyAction extends ActionSupport{

	private int pageIndex;
	private int displayCount;
	private long id;
	
	private String author;
	private String content;
	
	private InvitationService invitationService;

	
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
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getDisplayCount() {
		return displayCount;
	}
	public void setDisplayCount(int displayCount) {
		this.displayCount = displayCount;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public InvitationService getInvitationService() {
		return invitationService;
	}
	public void setInvitationService(InvitationService invitationService) {
		this.invitationService = invitationService;
	}
	
	/**
	 * 查询回复列表
	 * @return
	 */
	public String queryReply(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int result = (pageIndex - 1)*displayCount;
		List<Reply> replyToMessages = invitationService.getReplies(id, result, displayCount);
		int totalCount = (int) invitationService.getReplyCount(id);
		int totalPage = totalCount % displayCount == 0 ? totalCount / displayCount :totalCount / displayCount + 1;
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("pageIndex", pageIndex);
		session.setAttribute("id", id);
		session.setAttribute("displayCount", displayCount);
		session.setAttribute("replies", replyToMessages);
		return SUCCESS;
	}
	
	//添加回复
	public void add(){
		PrintWriter writer = null;
		try {
			writer = ServletActionContext.getResponse().getWriter();
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			System.out.println(date);
			Reply reply = new Reply();
			reply.setContent(content);
			if(author == null || "".equals(author)){
				author = "匿名用户";
			}
			reply.setAuthor(author);
			reply.setDate(date);
			Invitation invitation = new Invitation();
			invitation.setId(id);
			reply.setInvitation(invitation);
			boolean success = invitationService.add(reply);
			if(success){
				writer.print("success");
			}else{
				writer.print("failed");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		writer.close();
	}
	
}
