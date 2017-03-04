package com.crossover.prime.faces.beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.crossover.hibernate.data.User;
import com.crossover.rest.responseobjects.BuyTicketResponseObject;
import com.crossover.rest.responseobjects.OffersResponseObject;
import com.crossover.rest.responseobjects.ReturnObject;
import com.crossover.spring.service.UserServiceInterface;

@ManagedBean
@ViewScoped
public class BuyTicket {

	private String amount;

	// @ManagedProperty(value = "#{offers.listAllOffersReturnObjectTemp}")
	private OffersResponseObject listAllOffersReturnObject;

	@ManagedProperty("#{userService}")
	private UserServiceInterface userService;

	@PostConstruct
	private void init() {
		if (SessionUtils.getSession().getAttribute("ticket") == null) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			try {
				context.redirect(
						context.getRequestContextPath() + "/pages/user/protected/offers.xhtml?faces-redirect=true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public OffersResponseObject getListAllOffersReturnObject() {
		listAllOffersReturnObject = (OffersResponseObject) SessionUtils.getSession().getAttribute("ticket");
		return listAllOffersReturnObject;
	}

	public void setListAllOffersReturnObject(OffersResponseObject listAllOffersReturnObject) {
		this.listAllOffersReturnObject = listAllOffersReturnObject;
	}

	public UserServiceInterface getUserService() {
		return userService;
	}

	public void setUserService(UserServiceInterface userService) {
		this.userService = userService;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String buy() {
		try {
			User user = SessionUtils.getUser();
			ReturnObject returnObject = userService.purchaseTicket(user, Long.valueOf(amount),
					listAllOffersReturnObject.getRoute().getFrom(), listAllOffersReturnObject.getRoute().getTo());

			BuyTicketResponseObject buyTicketResponseObject = (BuyTicketResponseObject) returnObject
					.getServiceResponseObject();

			if (buyTicketResponseObject != null) {
				SessionUtils.getSession().setAttribute("buyTicketResponseObject", buyTicketResponseObject);
				return "/pages/user/protected/purchased-ticket-details.xhtml?faces-redirect=true";
			} else {
				String userMessage = "Generic Error Occured";
				if (returnObject.getErrorMessage().contains("400")) {
					userMessage = "Amount not valid or insufficient funds in your account";
				} else if (returnObject.getErrorMessage().contains("404")) {
					userMessage = "Applicant, airline route or account not found";
				}

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(userMessage));
				return "";
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			return "";
		}

	}

}
