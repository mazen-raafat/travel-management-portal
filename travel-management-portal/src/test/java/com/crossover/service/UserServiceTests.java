package com.crossover.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.hibernate.data.User;
import com.crossover.rest.objects.Currency;
import com.crossover.spring.service.UserServiceInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserServiceTests {
	
	@Autowired
	UserServiceInterface userService;
	
	@Test
	public void registerAndLoginShouldReturnTrue(){
		User user = new User();
		String randomEmail = Math.random()+"test@test.com";
		String password = "test";
		user.setEmail(randomEmail);
		user.setPassword(password);
		
		assertTrue(userService.register(user, Currency.USD));
		assertTrue(userService.login(user));
	}
	
	@Test
	public void registerationWithExistingEmailShouldReturnFalse(){
		User user = new User();
		String email = "test@test.com";
		String password = "test";
		user.setEmail(email);
		user.setPassword(password);
		userService.register(user, Currency.USD);
		assertFalse(userService.register(user, Currency.USD));
		
	}
	
	@Test
	public void loginWithNotExistingEmailShouldReturnFalse(){
		User user = new User();
		String randomEmail = Math.random()+"test@test.com";
		String password = "test";
		user.setEmail(randomEmail);
		user.setPassword(password);
		assertFalse(userService.login(user));
		
	}
	
	@Test
	public void loginWithWrongPasswordShouldReturnFalse(){
		User user = new User();
		String email = "test@test.com";
		String password = "falseP@ssw0rd";
		user.setEmail(email);
		user.setPassword(password);
		assertFalse(userService.login(user));
	}
	
	@Test
	public void loginWithWrongEmailAndPasswordShouldReturnFalse(){
		User user = new User();
		String email = "falseEmail@test.com";
		String password = "falseP@ssw0rd";
		user.setEmail(email);
		user.setPassword(password);
		assertFalse(userService.login(user));
	}
	

}
