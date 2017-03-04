package com.crossover.spring.models;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crossover.hibernate.data.PurchasedTickets;
import com.crossover.hibernate.data.User;

@Repository("PurchasedTicketsDAO")
public class PurchasedTicketsDAOImpl implements PurchasedTicketsDAO {

	private static final Logger logger = LoggerFactory.getLogger(PurchasedTicketsDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addPurchasedTickets(PurchasedTickets purchasedTickets) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(purchasedTickets);
		logger.info("purchasedTickets saved successfully, purchasedTickets Details=" + purchasedTickets);

	}

	@Override
	public void updatePurchasedTickets(PurchasedTickets purchasedTickets) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(purchasedTickets);
		logger.info("purchasedTickets updated successfully, purchasedTickets Details=" + purchasedTickets);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PurchasedTickets> listPurchasedTickets() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<PurchasedTickets> purchasedTicketsList = session.createQuery("from PurchasedTickets").list();
		for (PurchasedTickets PurchasedTickets : purchasedTicketsList) {
			logger.info("purchasedTicketsList List::" + PurchasedTickets);
		}
		return purchasedTicketsList;
	}

	@Override
	public PurchasedTickets getPurchasedTicketsById(int id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		PurchasedTickets purchasedTickets = (PurchasedTickets) session.load(PurchasedTickets.class, new Integer(id));
		logger.info("PurchasedTickets loaded successfully, PurchasedTickets details=" + purchasedTickets);
		return purchasedTickets;
	}

	@Override
	public void removePurchasedTickets(int id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		PurchasedTickets purchasedTickets = (PurchasedTickets) session.load(PurchasedTickets.class, new Integer(id));
		if (null != purchasedTickets) {
			session.delete(purchasedTickets);
		}
		logger.info("PurchasedTickets deleted successfully, PurchasedTickets details=" + purchasedTickets);

	}

	@Override
	public PurchasedTickets getPurchasedTicketsUserId(int id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("getPurchasedTicketsUserId");
		query.setParameter("userId", id);

		@SuppressWarnings("unchecked")
		List<PurchasedTickets> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

}
