package com.wechat.session;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionHandler {

	@Autowired
	private SessionFactory sessionFactory;
	
	public synchronized Session getSession() {
		Session session = null;

			if (null != sessionFactory)
				session = sessionFactory.openSession();
						
		return session;
	}
	
	public synchronized void returnSession(Session session) throws HibernateException {

		if (null != session && session.isOpen())
			session.close();
		else if (null != session && !session.isOpen()) {

			throw new HibernateException("Session is already closed !!!");
		}
		session = null;	
	}

}
