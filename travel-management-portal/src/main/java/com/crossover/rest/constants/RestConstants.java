package com.crossover.rest.constants;

import java.io.IOException;

import com.crossover.spring.utils.PropertiesReader;

public class RestConstants {

	// BASE URI
	public static String APPLICANT_ID;
	public static String BASE_URI;

	static {
		PropertiesReader propertiesReader = new PropertiesReader();
		try {
			APPLICANT_ID = propertiesReader.getPropValue("applicant_id");
			BASE_URI = propertiesReader.getPropValue("service_base_url")+APPLICANT_ID;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	//Reset API
	public static final String RESET_URI = "/reset";
	
	// GAMMA AIRLINES SERVICE URIS
	public static final String GAMMA_AIR_LINES_URI = "/gammaairlines";
	public static final String OFFERS_URI = "/offers";
	public static final String TICKETS_URI = "/tickets";
	public static final String BUY_URI = "/buy";

	// PAYPALLETS SERVICE URIS
	public static final String PAYPALLETS_URI = "/paypallets";
	public static final String ACCOUNT_URI = "/account";
	public static final String ACCOUNTS_URI = "/accounts";
	public static final String DEPOSIT_URI = "/deposit";
	public static final String WITHDRAW_URL = "/withdraw";

	// MONEY EXCHANGE SERVICE URIS
	public static final String MONEY_EXCHANGE_URI = "/moneyexchange";
	public static final String EXCHANGE_URI = "/exchange";

	// REST ERROR CODES
	public static final Integer REST_ERROR_CODE_200 = 200;
	public static final Integer REST_ERROR_CODE_400 = 400;
	public static final Integer REST_ERROR_CODE_404 = 404;

}
