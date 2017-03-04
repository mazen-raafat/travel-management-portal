package com.crossover.rest.responseobjects;

import com.crossover.rest.objects.Price;
import com.crossover.rest.objects.Route;

public class OffersResponseObject extends ServiceResponseObject{

	private Route route;
	private Price price;

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

}
