package cn.ljxt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ljxt.model.Invitation;
import cn.ljxt.service.InvitationService;
import net.sf.json.JSONObject;

/**
 * title有值表示模糊查询，title无值表示查询所有
 * @author Administrator
 *
 */
public class QueryInvitationAction extends ActionSupport{

	private int pageIndex;//页面传过来的当前页码
	private int displayCount;//页面传过来的条数
	private String title;//搜索条件
	private long id;//帖子ID
	
	
	private InvitationService invitationService;
	
	private HttpSession session = ServletActionContext.getRequest().getSession();
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public int getPageIndex() {
		return pageIndex;
	}



	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public int getDisplayCount() {
		return displayCount;
	}



	public void setDisplayCount(int displayCount) {
		this.displayCount = displayCount;
	}



	public InvitationService getInvitationService() {
		return invitationService;
	}



	public void setInvitationService(InvitationService invitationService) {
		this.invitationService = invitationService;
	}



	//展示所有的帖子
	public String queryMessages(){
		int result = (pageIndex - 1) * displayCount;
		List<Invitation> messages = invitationService.getMessages(title,result, displayCount);
		int totalCount = (int) invitationService.getCount(title);
		int totalPage = totalCount % displayCount == 0 ? totalCount / displayCount : totalCount / displayCount + 1;
		session.setAttribute("messages", messages);
		session.setAttribute("pageIndex", pageIndex);
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("title", title);
		return SUCCESS;
	}
	
	//删除
	public void delete(){
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = null;
		JSONObject json = new JSONObject();
		try {
			writer = response.getWriter();
			boolean success = invitationService.deleteSuccess(id);
			if(success){
				json.put("success", "success");
				int totalCount = (int) invitationService.getCount(title);
				json.put("totalCount", totalCount);
				writer.print(json);
			}else{
				json.put("failed", "failed");
				writer.print(json);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		writer.close();
		
	}
	
}
