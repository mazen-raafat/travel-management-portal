package com.crossover.spring.service;

import java.util.List;

import com.crossover.hibernate.data.PurchasedTickets;
import com.crossover.hibernate.data.User;

public interface AdminServiceInterface {

	List<User> getAllUsers();

	List<PurchasedTickets> getAllOrders();

}