package com.crossover.rest.requestobjects;

import com.crossover.rest.objects.Route;

public class BuyTicketsRequestParam {

	private String accountId;
	private long amount;
	private Route route;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}
