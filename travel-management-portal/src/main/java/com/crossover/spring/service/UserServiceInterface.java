package com.crossover.spring.service;

import com.crossover.hibernate.data.User;
import com.crossover.rest.objects.Currency;
import com.crossover.rest.responseobjects.BuyTicketResponseObject;
import com.crossover.rest.responseobjects.ReturnObject;

public interface UserServiceInterface {

	boolean register(User user, Currency currency);

	boolean login(User user);

	ReturnObject purchaseTicket(User user, long amount, String from, String to);

}