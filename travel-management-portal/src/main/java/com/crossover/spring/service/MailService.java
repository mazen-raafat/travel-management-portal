package com.crossover.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/*
 * Service used to send emails
 * */

@Service
public class MailService implements MessageServiceInterface{
	
	@Autowired
	private MailSender mailSender; // MailSender interface defines a strategy
										// for sending simple mails
 
	@Override
	public void sendMessage(String toAddress, String fromAddress, String subject, String msgBody) throws Exception{
 
		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom(fromAddress);
		crunchifyMsg.setTo(toAddress);
		crunchifyMsg.setSubject(subject);
		crunchifyMsg.setText(msgBody);
		mailSender.send(crunchifyMsg);
	}

}
