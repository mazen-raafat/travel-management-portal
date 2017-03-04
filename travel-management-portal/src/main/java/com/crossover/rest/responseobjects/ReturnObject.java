package com.crossover.rest.responseobjects;

public class ReturnObject {
	private String errorMessage;
	private ServiceResponseObject serviceResponseObject;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ServiceResponseObject getServiceResponseObject() {
		return serviceResponseObject;
	}

	public void setServiceResponseObject(ServiceResponseObject serviceResponseObject) {
		this.serviceResponseObject = serviceResponseObject;
	}

}
