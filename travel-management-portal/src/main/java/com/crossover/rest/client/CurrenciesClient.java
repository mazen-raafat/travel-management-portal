package com.crossover.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.crossover.rest.constants.RestConstants;
import com.crossover.rest.objects.Currency;
import com.crossover.rest.objects.MonetaryAmount;
import com.crossover.rest.requestobjects.ExchangeRequestParam;
import com.google.gson.Gson;

/*
 * 
 * All Currencies Services client:
 * exchange
 * 
 * */
public class CurrenciesClient {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrenciesClient.class);


	private static final String EXCHANGE_URL = RestConstants.BASE_URI + RestConstants.MONEY_EXCHANGE_URI + RestConstants.EXCHANGE_URI;

	public static String exchange(double amount, Currency fromCurrency, Currency targetCurrency) throws Exception {

		logger.info("Amount: " + amount + " ,fromCurrency: " + fromCurrency + " ,toCurrency: " + targetCurrency);
		
		RestTemplate restTemplate = new RestTemplate();

		Gson gson = new Gson();

		ExchangeRequestParam param = new ExchangeRequestParam();

		MonetaryAmount monetaryAmount = new MonetaryAmount();
		monetaryAmount.setAmount(amount);
		monetaryAmount.setCurrency(fromCurrency);

		param.setMonetaryAmount(monetaryAmount);
		param.setTargetCurrency(targetCurrency);

		String body = gson.toJson(param);
		System.out.println(body);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		String result = restTemplate.postForObject(EXCHANGE_URL, entity, String.class);
		
		logger.info("result: " + result);
		
		return result;

	}

}
