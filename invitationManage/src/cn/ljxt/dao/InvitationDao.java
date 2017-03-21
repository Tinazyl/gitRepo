package cn.ljxt.dao;

import java.util.List;

import cn.ljxt.model.Invitation;
import cn.ljxt.model.Reply;


public interface InvitationDao {

	
	//查询帖子
	List<Invitation> getMessages(String title,int result,int displayCount);
	
	//查询总条数
	long getCount(String title);
	
	//查询帖子的回复集合
	List<Reply> getReplies(long id,int result,int displayCount);
	
	//获取帖子的总回复数
	long getReplyCount(long id);
	
	//删除帖子
	int deleteInv(long id);
	
	//删除帖子的回复数
	int deleteReply(long id);
	
	//添加一条回复
	int add(Reply reply);
	
}
