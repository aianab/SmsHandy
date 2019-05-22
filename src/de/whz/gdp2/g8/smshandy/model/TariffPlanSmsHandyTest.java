package de.whz.gdp2.g8.smshandy.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;


public class TariffPlanSmsHandyTest {
	private String sender;
	private Provider provider;
	private TariffPlanSmsHandy phone;
	
	@BeforeEach
	public void init() throws NumberExistsException {
		sender = "123";
		provider = new Provider();
		phone = new TariffPlanSmsHandy(sender, provider);
	}
	@Test()
	void testCanSendSms() throws NumberExistsException {
		assertEquals(phone.canSendSms(), true);
	}
	
	
	@Test
	void testCanNotSendSms() throws NumberExistsException {
		for(int i = 0; i <=100; i++) {
			phone.payForSms();
		}
		assertEquals(phone.canSendSms(), false);
	}
	
	@Test
	void testPayForSms() throws NumberExistsException {
		int actualSmsAmount = 99;
		phone.payForSms();
		assertEquals(phone.getRemainingFreeSms(), actualSmsAmount);
	}

}
