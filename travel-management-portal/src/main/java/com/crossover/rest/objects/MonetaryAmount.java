package com.crossover.rest.objects;

public class MonetaryAmount {

	private double amount;
	private Currency currency;
	
	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public Currency getCurrency() {
		return currency;
	}



	public void setCurrency(Currency currency) {
		this.currency = currency;
	}



	@Override
	public String toString() {
		return "\"monetaryAmount\":{\"amount\":"+this.getAmount()+","+this.currency.toString()+"}";
	}
}
