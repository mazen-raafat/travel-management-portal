package com.crossover.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.rest.client.GammaAirlinesClient;
import com.crossover.rest.responseobjects.OffersResponseObject;
import com.crossover.spring.models.PurchasedTicketsDAO;

/*
 * 
 * GammaAirlines Tickets Services
 * 
 * */

@Service
public class GammaAirlinesService implements GammaAirlinesServiceInterface {

	private static final Logger logger = LoggerFactory.getLogger(GammaAirlinesService.class);

	@Autowired
	private PurchasedTicketsDAO purchasedTicketsDAO;

	/* (non-Javadoc)
	 * @see com.crossover.spring.service.GammaAirlinesServiceInterface#setPurchasedTicketsDAO(com.crossover.spring.models.PurchasedTicketsDAO)
	 */
	@Override
	public void setPurchasedTicketsDAO(PurchasedTicketsDAO purchasedTicketsDAO) {
		this.purchasedTicketsDAO = purchasedTicketsDAO;
	}
	
	/* (non-Javadoc)
	 * @see com.crossover.spring.service.GammaAirlinesServiceInterface#getGammaAirlinesOffers()
	 */
	@Override
	public List<OffersResponseObject> getGammaAirlinesOffers(){
		
		List<OffersResponseObject> allOffers;
		try {
			allOffers = GammaAirlinesClient.listAllOffers();
			logger.info("getGammaAirlinesOffers: " + allOffers);
		} catch (Exception e) {
			logger.error("GammaAirlinesClient.listAllOffers Exception: " + e.getMessage());
			return null;
		}
		
		return allOffers;
	}
	
	
}
