package com.crossover.spring.service;

import java.util.List;

import com.crossover.rest.responseobjects.OffersResponseObject;
import com.crossover.spring.models.PurchasedTicketsDAO;

public interface GammaAirlinesServiceInterface {

	void setPurchasedTicketsDAO(PurchasedTicketsDAO purchasedTicketsDAO);

	List<OffersResponseObject> getGammaAirlinesOffers();

}