package com.crossover.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.rest.client.ForestAPIClient;
import com.crossover.rest.client.PaypalletsClient;
import com.crossover.rest.objects.Currency;
import com.crossover.rest.responseobjects.CreateAccountResponseObject;
import com.crossover.rest.responseobjects.DepositWithdrawResponseObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class PaypalletsClientTests {

	/*
	 * Assert createAccount not throwws exception and dont return null object
	 */
	@Test
	public void createAccountTest() {
		CreateAccountResponseObject accountResponseObject = null;
		Exception exception = null;
		try {
			accountResponseObject = PaypalletsClient.createAccount(Currency.USD);
		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		} finally {
			assertNull(exception);
		}
		assertNotNull(accountResponseObject);
	}

	/*
	 * test deposit doesn't throws exception and doesn't return null and account
	 * money amount after deposit is true test withdraw doesn't throws exception
	 * and doesn't return null and account money amount after withdraw is true
	 */
	@Test
	public void depositAndWithdrawMoneyToAccountTest() {
		try {
			ForestAPIClient.reset();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Exception exception = null;
		DepositWithdrawResponseObject depositWithdrawResponseObject = null;

		try {
			depositWithdrawResponseObject = PaypalletsClient.depositMoneyToAccount("account1", 1000, Currency.USD);
		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		} finally {
			assertNull(exception);
		}

		assertNotNull(depositWithdrawResponseObject);
		assertEquals(depositWithdrawResponseObject.getBalance().getAmount(), 2000.0, 0.001);

		try {
			depositWithdrawResponseObject = PaypalletsClient.withdrawMoneyFromAccount("account1", 1000, Currency.USD);
		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		} finally {
			assertNull(exception);
		}

		assertNotNull(depositWithdrawResponseObject);
		assertEquals(depositWithdrawResponseObject.getBalance().getAmount(), 1000.0, 0.001);
	}
	
	/*
	 * 
	 * Assert listAllAccounts doesn't return null
	 * and doesn't throw exception
	 * 
	 * */

	@Test
	public void listAllAccountsTest() {
		String listAllAccounts = null;
		Exception exception = null;
		try {
			listAllAccounts = PaypalletsClient.listAllAccounts();
		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		} finally {
			assertNull(exception);
		}
		
		assertNotNull(listAllAccounts);
	}

}
