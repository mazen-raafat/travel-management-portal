package com.crossover.prime.faces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.crossover.hibernate.data.User;
import com.crossover.rest.objects.Currency;
import com.crossover.spring.service.UserServiceInterface;

@ManagedBean
@SessionScoped
public class RegisterUser {

	@ManagedProperty("#{userService}")
	private UserServiceInterface userService;

	private User user = new User();

	public UserServiceInterface getUserService() {
		return userService;
	}

	public void setUserService(UserServiceInterface userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String register() {

		if (userService.register(user, Currency.USD)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("The User with email: " + this.user.getEmail() + " Is Registered Successfully"));

			return "/pages/user/public/login.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("The User with email: "+this.user.getEmail()+" already exists"));

			return "";
		}

	}

}
