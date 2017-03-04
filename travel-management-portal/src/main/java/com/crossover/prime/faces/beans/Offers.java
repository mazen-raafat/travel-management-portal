package com.crossover.prime.faces.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.crossover.rest.responseobjects.OffersResponseObject;
import com.crossover.spring.service.GammaAirlinesServiceInterface;
import com.google.gson.Gson;

@ManagedBean
@ViewScoped
public class Offers {

	@ManagedProperty("#{gammaAirlinesService}")
	private GammaAirlinesServiceInterface gammaAirlinesService;

	public Long amount;

	private OffersResponseObject listAllOffersReturnObjectTemp;

	private List<OffersResponseObject> allOffersReturnObject = new ArrayList<OffersResponseObject>();

	public GammaAirlinesServiceInterface getGammaAirlinesService() {
		return gammaAirlinesService;
	}

	public void setGammaAirlinesService(GammaAirlinesServiceInterface gammaAirlinesService) {
		this.gammaAirlinesService = gammaAirlinesService;
	}

	public List<OffersResponseObject> getAllOffersReturnObject() {
		allOffersReturnObject = gammaAirlinesService.getGammaAirlinesOffers();
		return allOffersReturnObject;
	}

	public void setAllOffersReturnObject(List<OffersResponseObject> allOffersReturnObject) {
		this.allOffersReturnObject = allOffersReturnObject;
	}

	public OffersResponseObject getListAllOffersReturnObjectTemp() {
		return listAllOffersReturnObjectTemp;
	}

	public void setListAllOffersReturnObjectTemp(OffersResponseObject listAllOffersReturnObjectTemp) {
		this.listAllOffersReturnObjectTemp = listAllOffersReturnObjectTemp;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@SuppressWarnings("rawtypes")
	public String buyTicket(com.google.gson.internal.LinkedTreeMap o) {
		Gson gson = new Gson();
		OffersResponseObject offer = gson.fromJson(o.toString(), OffersResponseObject.class);
		listAllOffersReturnObjectTemp = offer;
		System.out.println(o.toString());
		
		SessionUtils.getSession().setAttribute("ticket", listAllOffersReturnObjectTemp);
		
		return "/pages/user/protected/buy-ticket.xhtml?faces-redirect=true";
	}



}
