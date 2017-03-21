package cn.ljxt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ljxt.dao.InvitationDao;
import cn.ljxt.model.Invitation;
import cn.ljxt.model.Reply;
import cn.ljxt.service.InvitationService;

public class InvitationServiceImpl implements InvitationService{
	
	
	private InvitationDao dao;
	
	

	public InvitationDao getDao() {
		return dao;
	}

	public void setDao(InvitationDao dao) {
		this.dao = dao;
	}



	@Override
	public List<Invitation> getMessages(String title,int result,int displayCount) {
		
		return dao.getMessages(title,result,displayCount);
	}



	@Override
	public long getCount(String title) {
		
		return dao.getCount(title);
	}

	@Override
	public List<Reply> getReplies(long id,int result,int displayCount) {
		
		return dao.getReplies(id,result,displayCount);
	}

	@Override
	public long getReplyCount(long id) {
		return dao.getReplyCount(id);
	}

	
	@Override
	public boolean deleteSuccess(long id) {
		int count = (int) dao.getReplyCount(id);
		int count2 = dao.deleteReply(id);
		int count1 = dao.deleteInv(id);
		try {
			if(count1 == 1 && count2 == count){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(Reply reply) {
		return dao.add(reply) == 1;
	}
	
	
	

}
