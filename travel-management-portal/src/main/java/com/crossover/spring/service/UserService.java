package com.crossover.spring.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.hibernate.data.PurchasedTickets;
import com.crossover.hibernate.data.User;
import com.crossover.rest.client.GammaAirlinesClient;
import com.crossover.rest.client.PaypalletsClient;
import com.crossover.rest.objects.Currency;
import com.crossover.rest.responseobjects.BuyTicketResponseObject;
import com.crossover.rest.responseobjects.CreateAccountResponseObject;
import com.crossover.rest.responseobjects.DepositWithdrawResponseObject;
import com.crossover.rest.responseobjects.ReturnObject;
import com.crossover.spring.models.PurchasedTicketsDAO;
import com.crossover.spring.models.UserDAO;

/*
 * All Services needed for User
 * 
 * register
 * login
 * purchase tickets
 * 
 * */

@Service
public class UserService implements UserServiceInterface {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PurchasedTicketsDAO purchasedTicketsDAO;
	

	public void setUserDao(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/* (non-Javadoc)
	 * @see com.crossover.spring.service.UserServiceInterface#register(com.crossover.hibernate.data.User, com.crossover.rest.client.paypallets.Currency)
	 */
	@Override
	@Transactional
	public boolean register(User user, Currency currency) {

		User u = null;
		try {
			u = userDAO.getUserByEmail(user.getEmail());
		} catch (Exception e) {
			logger.error("userDAO.getUserByEmailException " + e.getMessage());
			return false;
		}

		if (u == null) {

			
			CreateAccountResponseObject createAccountReturnObject = null;
			try {
				createAccountReturnObject = PaypalletsClient.createAccount(currency);
				logger.info("createAccountReturnObject " + createAccountReturnObject);
			} catch (Exception e) {
				logger.error("PaypalletsClient.createAccount Exception " + e.getMessage());
				return false;
			}
			
			
			DepositWithdrawResponseObject depositWithdrawReturnObject = null;
			try {
				depositWithdrawReturnObject = PaypalletsClient
						.depositMoneyToAccount(createAccountReturnObject.getId(), 1000, currency);
				logger.info("depositWithdrawReturnObject " + depositWithdrawReturnObject);
			} catch (Exception e) {
				logger.error("PaypalletsClient.depositMoneyToAccount Exception " + e.getMessage());
				return false;
			}
			
			
			user.setPaypalletAccountId(createAccountReturnObject.getId());
			user.setPaypalletAccountAmount(depositWithdrawReturnObject.getBalance().getAmount());
			user.setPaypalletAccountCurrency(depositWithdrawReturnObject.getBalance().getCurrency());
			try {
				userDAO.addUser(user);
				logger.info("User added to database " + user.toString());
			} catch (Exception e) {
				logger.error("userDAO.addUser Exception " + e.getMessage());
				return false;
			}
			
			
			return true;
		}else{
			logger.info("User found in database");
			return false;
		}

		

	}

	/* (non-Javadoc)
	 * @see com.crossover.spring.service.UserServiceInterface#login(com.crossover.hibernate.data.User)
	 */
	@Override
	@Transactional
	public boolean login(User user) {
				
		
		User u = null;
		try {
			u = userDAO.getUserByEmail(user.getEmail());
			logger.info("userDAO.getUserByEmail " + u.toString());
		} catch (Exception e) {
			logger.error("userDAO.getUserByEmail Exception " + e.getMessage());
			return false;
		}
		if (user.getPassword().equalsIgnoreCase(u.getPassword())) {
			user.setUserId(u.getUserId());
			user.setPaypalletAccountAmount(u.getPaypalletAccountAmount());
			user.setPaypalletAccountCurrency(u.getPaypalletAccountCurrency());
			user.setPaypalletAccountId(u.getPaypalletAccountId());
			user.setPurchasedTickets(u.getPurchasedTickets());
			logger.info("user loggedin");
			return true;
		}else{
			logger.info("user password is incorrect");
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.crossover.spring.service.UserServiceInterface#purchaseTicket(com.crossover.hibernate.data.User, long, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public ReturnObject purchaseTicket(User user, long amount, String from, String to) {
		
		BuyTicketResponseObject buyTicketResponseObject;
		ReturnObject returnObject = new ReturnObject();
		try {
			returnObject = GammaAirlinesClient.buyTickets(user.getPaypalletAccountId(), amount, from, to);
			buyTicketResponseObject = (BuyTicketResponseObject) returnObject.getServiceResponseObject();
			logger.info("buyTicketResponseObject: "+ buyTicketResponseObject.toString());
		} catch (Exception e) {
			logger.error("GammaAirlinesClient.buyTickets exception: " + e.getMessage());
			returnObject.setErrorMessage(e.getMessage());
			return returnObject;
		}
		
		
		
		PurchasedTickets purchasedTickets = new PurchasedTickets();
		purchasedTickets.setAmount(buyTicketResponseObject.getAmount());		
		purchasedTickets.setUser(user);
		purchasedTickets.setFromPlace(buyTicketResponseObject.getDetails().getRoute().getFrom());
		purchasedTickets.setToPlace(buyTicketResponseObject.getDetails().getRoute().getTo());
		purchasedTickets.setPrice(buyTicketResponseObject.getDetails().getPrice().getAmount());
		purchasedTickets.setCurrency(buyTicketResponseObject.getDetails().getPrice().getCurrency());
		purchasedTickets.setPurchasingDate(new Date());
		
		try {
			purchasedTicketsDAO.addPurchasedTickets(purchasedTickets);
		} catch (Exception e) {
			logger.error("purchasedTicketsDAO.addPurchasedTickets Exception: " + e.getMessage());
			returnObject.setErrorMessage(e.getMessage());
			return returnObject;
		}
		
		logger.info("purchasedTickets saved in database: "+purchasedTickets.toString());
		return returnObject;
	}

}
