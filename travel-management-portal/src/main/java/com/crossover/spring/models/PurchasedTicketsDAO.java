package com.crossover.spring.models;

import java.util.List;

import com.crossover.hibernate.data.PurchasedTickets;


public interface PurchasedTicketsDAO {

	public void addPurchasedTickets(PurchasedTickets purchasedTickets) throws Exception;

	public void updatePurchasedTickets(PurchasedTickets purchasedTickets) throws Exception;

	public List<PurchasedTickets> listPurchasedTickets() throws Exception;

	public PurchasedTickets getPurchasedTicketsById(int id) throws Exception;

	public void removePurchasedTickets(int id) throws Exception;
	
	public PurchasedTickets getPurchasedTicketsUserId(int id) throws Exception;
}
