package cn.ljxt.service;

import java.util.List;

import cn.ljxt.model.Invitation;
import cn.ljxt.model.Reply;

public interface InvitationService {

	
	List<Invitation> getMessages(String title,int result,int displayCount);
	
	long getCount(String title);
	
	List<Reply> getReplies(long id,int result,int displayCount);
	
	long getReplyCount(long id);
	
	boolean deleteSuccess(long id);
	
	boolean add(Reply reply);
}
