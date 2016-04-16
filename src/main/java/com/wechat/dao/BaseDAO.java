package com.wechat.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wechat.session.SessionHandler;


@Repository
public abstract class BaseDAO {

	
	private static final Logger log = Logger.getAnonymousLogger();
	private static final ThreadLocal sessionThread = new ThreadLocal();
    //@SuppressWarnings("deprecation")
	//private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    
    
      
    @Autowired
	private SessionHandler sessionHandler;
	protected Session getSession(){
		return sessionHandler.getSession();
	}
	
	protected void returnSession(Session session){
		sessionHandler.returnSession(session);
	}
	

    /*
    public static Session getSession()
    {
        Session session = (Session) BaseDAO.sessionThread.get();
        
        if (session == null)
        {
            session = sessionFactory.openSession();
            BaseDAO.sessionThread.set(session);
        }
        return session;
    }
	*/
    protected void begin(Session session) {
        session.beginTransaction();
    }

    protected void commit(Session session) {
        session.getTransaction().commit();
    }

    protected void rollback(Session session) {
        try {
        	session.getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
        	session.close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        //BaseDAO.sessionThread.set(null);
    }

    public static void close() {
        //getSession().close();
        //BaseDAO.sessionThread.set(null);
    }
}
