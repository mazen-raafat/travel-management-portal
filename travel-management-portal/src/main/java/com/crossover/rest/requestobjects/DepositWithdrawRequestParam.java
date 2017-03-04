package com.crossover.rest.requestobjects;

import com.crossover.rest.objects.MonetaryAmount;

public class DepositWithdrawRequestParam {

	private String accountId;
	private MonetaryAmount monetaryAmount;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public MonetaryAmount getMonetaryAmount() {
		return monetaryAmount;
	}

	public void setMonetaryAmount(MonetaryAmount monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}

}
