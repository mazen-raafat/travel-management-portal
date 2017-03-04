package com.crossover.rest.requestobjects;

import com.crossover.rest.objects.Currency;
import com.crossover.rest.objects.MonetaryAmount;

public class ExchangeRequestParam {

	private MonetaryAmount monetaryAmount;
	private Currency targetCurrency;

	public MonetaryAmount getMonetaryAmount() {
		return monetaryAmount;
	}

	public void setMonetaryAmount(MonetaryAmount monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}

	public Currency getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(Currency targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	

}
