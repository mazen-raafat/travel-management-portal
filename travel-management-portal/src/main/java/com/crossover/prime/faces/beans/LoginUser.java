package com.crossover.prime.faces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.crossover.hibernate.data.User;
import com.crossover.spring.service.UserServiceInterface;

@ManagedBean
@SessionScoped
public class LoginUser {

	@ManagedProperty("#{userService}")
	private UserServiceInterface userService;

	private boolean loggedin = false;
	
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

	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public String login() {

		boolean login=false;
		try{
			login = userService.login(user);
			if (login) {
				// Add message
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Welcome " + this.user.getEmail()));
				SessionUtils.setUser(user);
				loggedin = true;
				return "/pages/user/protected/offers.xhtml?faces-redirect=true";
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Wrong email or password"));
				return "";
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error while login: " + e.getMessage()));
			return "";
		}
		
	}
	
	public String logout(){
		SessionUtils.getSession().invalidate();
		loggedin = false;
		return "/pages/user/public/login.xhtml?faces-redirect=true";
	}

}
