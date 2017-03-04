package com.crossover.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.rest.client.ForestAPIClient;
import com.crossover.rest.client.GammaAirlinesClient;
import com.crossover.rest.responseobjects.OffersResponseObject;
import com.crossover.rest.responseobjects.ReturnObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class GammaAirlinesClientTest {

	/*
	 * Assert that the method doesn't throw exception
	 * return not null list
	 * and list size = 6
	 *  
	 * */
	@Test
	public void listAllOffersTest(){
		Exception exception = null;
		List<OffersResponseObject> list = null;
		try {
			list = GammaAirlinesClient.listAllOffers();
		} catch (Exception e) {
			exception = e;
		} finally{
			assertNull(exception);
		}
		
		assertNotNull(list);
		assertEquals(list.size(), 6);
	}
	
	/*
	 * 
	 * assert listAllAccounts not throws exception
	 * and return not null
	 * 
	 * */
	@Test
	public void listAllTicketsTest(){
		Exception exception = null;
		String s = null;
		try {
			s = GammaAirlinesClient.listAllTickets();
		} catch (Exception e) {
			exception = e;
		} finally{
			assertNull(exception);
		}
		
		assertNotNull(s);
		
	}
	
	/*
	 * 
	 * Assert that buy ticket method doesn't throw exception
	 * return object not null
	 * and error message in null
	 * 
	 * */
	@Test 
	public void buyTicketsTest(){
		Exception exception = null;
		ReturnObject returnObject = null;
		try {
			ForestAPIClient.reset();
			returnObject = GammaAirlinesClient.buyTickets("account1", 2, "London", "Madrid");
		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		} finally{
			assertNull(exception);
		}
		
		assertNotNull(returnObject);
		assertNotNull(returnObject.getServiceResponseObject());
		assertNull(returnObject.getErrorMessage());

		
	}
	
}
