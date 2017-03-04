package com.crossover.rest.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.crossover.rest.constants.RestConstants;
import com.crossover.rest.objects.Route;
import com.crossover.rest.requestobjects.BuyTicketsRequestParam;
import com.crossover.rest.responseobjects.BuyTicketResponseObject;
import com.crossover.rest.responseobjects.OffersResponseObject;
import com.crossover.rest.responseobjects.ReturnObject;
import com.google.gson.Gson;

/*
 * All GammaAirlines Service Client:
 * 
 * listAllOffers
 * listAllTickets
 * buyTickets
 * 
 * */
public class GammaAirlinesClient {

	private static final Logger logger = LoggerFactory.getLogger(GammaAirlinesClient.class);

	private static final String OFFERS_URL = RestConstants.BASE_URI + RestConstants.GAMMA_AIR_LINES_URI + RestConstants.OFFERS_URI;
	private static final String BUY_TICKETS_URL = RestConstants.BASE_URI + RestConstants.GAMMA_AIR_LINES_URI + RestConstants.TICKETS_URI + RestConstants.BUY_URI;
	private static final String TICKETS_URL = RestConstants.BASE_URI + RestConstants.GAMMA_AIR_LINES_URI + RestConstants.TICKETS_URI;

	@SuppressWarnings("unchecked")
	public static List<OffersResponseObject> listAllOffers() throws Exception{
		Gson gson = new Gson();
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(OFFERS_URL, String.class);
		logger.info("result: " + result);
		List<OffersResponseObject> listAllOffersReturnObject = (List<OffersResponseObject>) gson.fromJson(result,List.class);
		return listAllOffersReturnObject;
	}
	
	public static String listAllTickets() throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(TICKETS_URL, String.class);
		logger.info("result: " + result);
		return result;
	}
	
	public static ReturnObject buyTickets(String accountId, long amount, String from, String to) throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		BuyTicketsRequestParam param = new BuyTicketsRequestParam();
		
		Route route = new Route();
		route.setFrom(from);
		route.setTo(to);
		
		param.setRoute(route);
		param.setAccountId(accountId);
		param.setAmount(amount);
		
		String body = gson.toJson(param);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		String result = restTemplate.postForObject(BUY_TICKETS_URL, entity, String.class);
		logger.info("result: " + result);
		
		ReturnObject returnObject = new ReturnObject(); 
		try{
			BuyTicketResponseObject buyTicketResponseObject = gson.fromJson(result, BuyTicketResponseObject.class);
			returnObject.setServiceResponseObject(buyTicketResponseObject);
			
		}catch (Exception e) {
			returnObject.setErrorMessage(e.getMessage());
		}
		return returnObject;
		
	}
}
