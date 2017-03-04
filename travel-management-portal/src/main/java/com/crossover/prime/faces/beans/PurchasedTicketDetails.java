package com.crossover.prime.faces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.crossover.hibernate.data.User;
import com.crossover.rest.responseobjects.BuyTicketResponseObject;
import com.crossover.spring.service.MailService;

@ManagedBean
@ViewScoped
public class PurchasedTicketDetails {
	
	private BuyTicketResponseObject buyTicketResponseObject;

	@ManagedProperty("#{mailService}")
	private MailService mailService;
	
	public BuyTicketResponseObject getBuyTicketResponseObject() {
		buyTicketResponseObject = (BuyTicketResponseObject) SessionUtils.getSession().getAttribute("buyTicketResponseObject");
		return buyTicketResponseObject;
	}

	public void setBuyTicketResponseObject(BuyTicketResponseObject buyTicketResponseObject) {
		this.buyTicketResponseObject = buyTicketResponseObject;
	}
	
	
	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void sendEmail(){
		User user = SessionUtils.getUser();
		String mailBody="Your Purchased Ticket Details: \nAmount of Tickets: "+buyTicketResponseObject.getAmount()+"\nFrom:" + buyTicketResponseObject.getDetails().getRoute().getFrom() +"\n To: "+buyTicketResponseObject.getDetails().getRoute().getTo()+"\n Price:"+buyTicketResponseObject.getDetails().getPrice().getAmount()+" "+buyTicketResponseObject.getDetails().getPrice().getCurrency();		
		try {
			mailService.sendMessage(user.getEmail(), "noreply.mazen@gmail.com", "Purchased Ticket Details", mailBody);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Email Sent Successfully"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Failed to send Mail"));
		}	
		
	}
	

}
