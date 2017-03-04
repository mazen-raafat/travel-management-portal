package com.crossover.spring.models;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crossover.hibernate.data.User;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(User.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addUser(User u) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
		logger.info("User saved successfully, User Details=" + u);
	}

	@Override
	public void updateUser(User u) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
		logger.info("User updated successfully, User Details=" + u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("from User").list();
		for (User u : userList) {
			logger.info("User List::" + u);
		}
		return userList;
	}

	@Override
	public User getUserById(Long id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Long(id));
		logger.info("User loaded successfully, User details=" + u);
		return u;
	}

	@Override
	public void removeUser(int id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Integer(id));
		if (null != u) {
			session.delete(u);
		}
		logger.info("User deleted successfully, user details=" + u);
	}
	
	@Override
	public User getUserByEmail(String email)throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("getUserByEmail");
		query.setString("email", email);
		
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		if (list != null && list.size()>0) {
			return  list.get(0);
		}
		
		return null;
	}

}
