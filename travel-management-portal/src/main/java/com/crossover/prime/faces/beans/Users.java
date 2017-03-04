package com.crossover.prime.faces.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.crossover.hibernate.data.User;
import com.crossover.spring.service.AdminServiceInterface;

@ManagedBean
@ViewScoped
public class Users {

	private List<User> usersList = new ArrayList<User>();
	
	@ManagedProperty("#{adminService}")
	private AdminServiceInterface adminService;

	public List<User> getUsersList() {
		usersList = adminService.getAllUsers();
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public AdminServiceInterface getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminServiceInterface adminService) {
		this.adminService = adminService;
	}
	
	
}
