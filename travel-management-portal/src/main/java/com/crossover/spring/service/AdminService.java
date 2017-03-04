package com.crossover.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.hibernate.data.PurchasedTickets;
import com.crossover.hibernate.data.User;
import com.crossover.spring.models.PurchasedTicketsDAO;
import com.crossover.spring.models.UserDAO;

/*
 * All Services needed for Admin
 * 
 * getAllUsers
 * getAllOrders
 * 
 * */

@Service
public class AdminService implements AdminServiceInterface {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private PurchasedTicketsDAO purchasedTicketsDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	/* (non-Javadoc)
	 * @see com.crossover.spring.service.AdminServiceInterface#getAllUsers()
	 */
	@Override
	@Transactional
	public List<User> getAllUsers(){
		List<User> users;
		try {
			users = userDAO.listUsers();
			logger.info("getAllUsers: " + users.toString());
		} catch (Exception e) {
			logger.error("userDAO.listUsers Exception: " + e.getMessage());
			return null;
		}
		return users;
		
	}
	
	/* (non-Javadoc)
	 * @see com.crossover.spring.service.AdminServiceInterface#getAllOrders()
	 */
	@Override
	@Transactional
	public List<PurchasedTickets> getAllOrders(){
		List<PurchasedTickets> purchasedTickets;
		try {
			purchasedTickets = purchasedTicketsDAO.listPurchasedTickets();
			logger.info("getAllOrders: " + purchasedTickets);
		} catch (Exception e) {
			logger.error("purchasedTicketsDAO.listPurchasedTickets Exception: " + e.getMessage());
			return null;
		};
		
		return purchasedTickets;
	}
}
