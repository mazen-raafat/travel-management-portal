package com.crossover.spring.service;

public interface MessageServiceInterface {

	void sendMessage(String toAddress, String fromAddress, String subject, String msgBody) throws Exception;
}
