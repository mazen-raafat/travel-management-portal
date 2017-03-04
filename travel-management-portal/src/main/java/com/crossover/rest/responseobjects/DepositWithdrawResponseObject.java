package com.crossover.rest.responseobjects;

import com.crossover.rest.objects.Balance;

public class DepositWithdrawResponseObject extends ServiceResponseObject{

	private String id;
	private Balance balance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

}
