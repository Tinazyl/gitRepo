package cn.ljxt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.ljxt.dao.InvitationDao;

import cn.ljxt.model.Invitation;
import cn.ljxt.model.Reply;

public class InvitationDaoImpl implements InvitationDao{

	private SessionFactory sessionFactory;
	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}



	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/**
	 * 没输入条件代表查询所有
	 */
	@Override
	public List<Invitation> getMessages(String title,int result,int displayCount) {
		Session session = sessionFactory.openSession();
		StringBuilder builder = new StringBuilder("from Invitation where 1=1");
		Query query = null;
		if(!"".equals(title) && title != null){
			builder.append("and title like ?");
			builder.append("order by date desc");
			query = session.createQuery(builder.toString());
			query.setParameter(0, "%"+title+"%");
		}else{
			builder.append("order by date desc");
			query = session.createQuery(builder.toString());
		}
		query.setFirstResult(result);
		query.setMaxResults(displayCount);
		List<Invitation> lists = query.list();
		return lists;
	}



	@Override
	public long getCount(String title) {
		Session session = sessionFactory.openSession();
		StringBuilder builder = new StringBuilder("select count(*) from Invitation where 1=1");
		Query query = null;
		if(!"".equals(title) && title != null){
			builder.append("and title like ?");
			query = session.createQuery(builder.toString());
			query.setParameter(0, "%"+title+"%");
		}else{
			query = session.createQuery(builder.toString());
		}
		long count = (long) query.uniqueResult();
		return count;
		
	}
	@Override
	public List<Reply> getReplies(long id,int result,int displayCount) {
		Session session = sessionFactory.openSession();
		//Query query = session.createSQLQuery("select id,content,author,createdate,invid from reply_detail where invid = ? order by createdate desc").addEntity(Reply.class);
		Query query = session.createQuery("from Reply where invitation.id=? order by date desc");
		query.setParameter(0, id);
		query.setFirstResult(result);
		query.setMaxResults(displayCount);
		return query.list();
	}



	@Override
	public long getReplyCount(long id) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select count(*) from Reply where invitation.id=?");
		query.setParameter(0, id);
		return (long) query.uniqueResult();
	}



	@Override
	public int deleteInv(long id) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("delete from Invitation where id=?");
		query.setParameter(0, id);
		int count = query.executeUpdate();
		return count;
	}



	@Override
	public int deleteReply(long id) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("delete from Reply where invitation.id=?");
		query.setParameter(0, id);
		int count = query.executeUpdate();
		return count;
	}



	@Override
	public int add(Reply reply) {
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery("insert into reply_detail(content,author,createdate,invid) values(?,?,?,?)").addEntity(Reply.class);
		query.setParameter(0, reply.getContent());
		query.setParameter(1, reply.getAuthor());
		query.setParameter(2, reply.getDate());
		query.setParameter(3, reply.getInvitation().getId());
		int count = query.executeUpdate();
		return count;
		
	}

}
