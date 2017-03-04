package com.crossover.rest.responseobjects;

import com.crossover.rest.objects.Details;

public class BuyTicketResponseObject extends ServiceResponseObject{

	private long amount;
	private Details details;
	

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

}
