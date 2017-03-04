package com.crossover.prime.faces.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.crossover.hibernate.data.PurchasedTickets;
import com.crossover.spring.service.AdminServiceInterface;

@ManagedBean
@ViewScoped
public class Orders {

	private List<PurchasedTickets> ordersList = new ArrayList<PurchasedTickets>();
	
	@ManagedProperty("#{adminService}")
	private AdminServiceInterface adminService;

	
	public List<PurchasedTickets> getOrdersList() {
		ordersList = adminService.getAllOrders();
		return ordersList;
	}

	public void setOrdersList(List<PurchasedTickets> ordersList) {
		this.ordersList = ordersList;
	}

	public AdminServiceInterface getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminServiceInterface adminService) {
		this.adminService = adminService;
	}
	
	
	
	
	
	
}
