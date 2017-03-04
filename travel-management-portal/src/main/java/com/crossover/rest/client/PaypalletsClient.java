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
import com.crossover.rest.requestobjects.CreateAccountRequestParam;
import com.crossover.rest.requestobjects.DepositWithdrawRequestParam;
import com.crossover.rest.responseobjects.CreateAccountResponseObject;
import com.crossover.rest.responseobjects.DepositWithdrawResponseObject;
import com.google.gson.Gson;

/*
 * All Paypallets Service Client:
 * 
 * createAccount
 * listAllAccounts
 * depositMoneyToAccount
 * withdrawMoneyToAccount
 * 
 * 
 * */

public class PaypalletsClient {

	private static final Logger logger = LoggerFactory.getLogger(PaypalletsClient.class);
	
	private static final String CREATE_ACCOUNT_URL = RestConstants.BASE_URI + RestConstants.PAYPALLETS_URI + RestConstants.ACCOUNT_URI;
	private static final String LIST_ALL_ACCOUNTS_URI = RestConstants.BASE_URI + RestConstants.PAYPALLETS_URI + RestConstants.ACCOUNTS_URI;
	private static final String DEPOSIT_MONEY_URL = RestConstants.BASE_URI + RestConstants.PAYPALLETS_URI + RestConstants.ACCOUNT_URI + RestConstants.DEPOSIT_URI;
	private static final String WITHDRAW_MONEY_URL = RestConstants.BASE_URI + RestConstants.PAYPALLETS_URI + RestConstants.ACCOUNT_URI + RestConstants.WITHDRAW_URL;

	public static CreateAccountResponseObject createAccount(Currency currency) throws Exception{
		RestTemplate restTemplate = new RestTemplate();

		Gson gson = new Gson();
		CreateAccountRequestParam param = new CreateAccountRequestParam();
		param.setCurrency(currency);

		String body = gson.toJson(param);
		System.out.println(body);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		String result = restTemplate.postForObject(CREATE_ACCOUNT_URL, entity, String.class);
		logger.info("result: " + result);
		CreateAccountResponseObject accountReturnObject = gson.fromJson(result, CreateAccountResponseObject.class);
		
		return accountReturnObject;

	}

	public static String listAllAccounts() throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(LIST_ALL_ACCOUNTS_URI, String.class);
		logger.info("result: " + result);
		return result;
	}

	public static DepositWithdrawResponseObject depositMoneyToAccount(String accountId, long amount, Currency currency) throws Exception {
		return depositWithdrowMoneyToAccount(DEPOSIT_MONEY_URL, accountId, amount, currency);
	}
	
	public static DepositWithdrawResponseObject withdrawMoneyFromAccount(String accountId, long amount, Currency currency) throws Exception {
		return depositWithdrowMoneyToAccount(WITHDRAW_MONEY_URL,accountId,amount,currency);
	}
	
	private static DepositWithdrawResponseObject depositWithdrowMoneyToAccount(String URL,String accountId, long amount, Currency currency) throws Exception{
		RestTemplate restTemplate = new RestTemplate();

		Gson gson = new Gson();

		DepositWithdrawRequestParam param = new DepositWithdrawRequestParam();

		MonetaryAmount monetaryAmount = new MonetaryAmount();
		monetaryAmount.setAmount(amount);
		monetaryAmount.setCurrency(currency);

		param.setAccountId(accountId);
		param.setMonetaryAmount(monetaryAmount);

		String body = gson.toJson(param);
		System.out.println(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		String result = restTemplate.postForObject(URL, entity, String.class);
		logger.info("result: " + result);
		DepositWithdrawResponseObject returnObject = gson.fromJson(result, DepositWithdrawResponseObject.class);
		return returnObject;
	}

}
