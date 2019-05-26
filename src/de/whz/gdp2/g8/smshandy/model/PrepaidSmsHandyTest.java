package de.whz.gdp2.g8.smshandy.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;

class PrepaidSmsHandyTest {

	private String sender;
	private Provider provider;
	private PrepaidSmsHandy phone;

	@BeforeEach
	public void init() throws Exception {
		sender = "123";
		provider = new Provider("");
		phone = new PrepaidSmsHandy(sender, provider);
	}

	@Test
	void testPayForSms() throws Exception {
		int actualBalance = 90;
		phone.payForSms();
		assertEquals(provider.getCreditForSmsHandy(phone.getNumber()), actualBalance);
	}

	@Test
	void testDeposit() throws Exception {
		int actualBalance = 200;
		phone.deposit(100);
		assertEquals(provider.getCreditForSmsHandy(phone.getNumber()), actualBalance);
	}

	@Test
	void testCanSendSms() throws NumberExistsException {
		assertEquals(phone.canSendSms(), true);
	}

	@Test
	void testCanNotSendSms() throws Exception {
		provider.deposit(sender, -100);
		assertEquals(phone.canSendSms(), false);
	}
}
