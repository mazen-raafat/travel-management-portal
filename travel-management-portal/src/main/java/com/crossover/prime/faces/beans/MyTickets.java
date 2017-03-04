package com.crossover.prime.faces.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.crossover.hibernate.data.PurchasedTickets;

@ManagedBean
@ViewScoped
public class MyTickets {

	Set<PurchasedTickets> purchasedTickets = new HashSet<PurchasedTickets>();

	public Set<PurchasedTickets> getPurchasedTickets() {
		purchasedTickets = SessionUtils.getUser().getPurchasedTickets();
		if (purchasedTickets == null || purchasedTickets.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("You dont have any purchased tickets"));
		}
		return purchasedTickets;
	}

	public void setPurchasedTickets(Set<PurchasedTickets> purchasedTickets) {
		this.purchasedTickets = purchasedTickets;
	}
	
	
	
}
