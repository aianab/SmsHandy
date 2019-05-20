package de.whz.gdp2.g8.smshandy.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;

class PrepaidSmsHandyTest {

	String sender;
	Provider provider;
	PrepaidSmsHandy phone;
	
	@BeforeEach
	public void init() throws NumberExistsException {
	sender = "123";
	provider = new Provider();
	phone = new PrepaidSmsHandy(sender, provider);
	}
	
	@Test
	void testPayForSms() throws NumberExistsException, NotEnoughBalanceException {
		Integer actualBalance = 90;
		phone.payForSms();
		assertEquals(provider.getCredits().get(sender), actualBalance);
	}

	@Test
	void testDeposit() throws NumberExistsException{
		Integer actualBalance = 200;
		phone.deposit(100);
		assertEquals(provider.getCredits().get(sender), actualBalance);
	}
	
	@Test
	void testCanSendSms() throws NumberExistsException{
		provider.getCredits().put(sender, 20);
		assertEquals(phone.canSendSms(), true);
	}
	
	@Test
	void testCanNotSendSms() throws NumberExistsException{
		provider.getCredits().put(sender, 0);
		assertEquals(phone.canSendSms(), false);
	}
}
