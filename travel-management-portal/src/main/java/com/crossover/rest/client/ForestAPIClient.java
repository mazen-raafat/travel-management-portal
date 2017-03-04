package com.crossover.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.crossover.rest.constants.RestConstants;

public class ForestAPIClient {

	private static final String RESET_URL = RestConstants.BASE_URI + RestConstants.RESET_URI;

	private static final Logger logger = LoggerFactory.getLogger(ForestAPIClient.class);


	public static String reset() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(RESET_URL, String.class);
		
		logger.info("result: " + result);
		
		return result;

	}
}
