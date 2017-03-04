package com.crossover.service;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.spring.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class MailServiceTests {
	
	@Autowired
	MailService mailService;
	
	@Test
	public void mailServiceIsNotThrowingException(){
		Exception exception = null;
		try {
			mailService.sendMessage("test@test.com", "noreply.mazen@gmail.com", "unit testing", "test test");
		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		}finally {
			assertNull(exception);
		}
	}

}
