package de.whz.gdp2.g8.smshandy.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;


public class TariffPlanSmsHandyTest {
	private String sender;
	private Provider provider;
	private TariffPlanSmsHandy phone;
	
	@BeforeEach
	public void init() throws NumberExistsException, NumberNotExistException, ProviderNotGivenException {
		sender = "123";
		provider = new Provider("");
		phone = new TariffPlanSmsHandy(sender, provider);
	}
	@Test()
	void testCanSendSms() throws Exception {
		assertEquals(phone.canSendSms(), true);
	}
	
	@Test
	void testPayForSms() throws NumberExistsException, NotEnoughBalanceException {
		int actualSmsAmount = 99;
		phone.payForSms();
		assertEquals(phone.getRemainingFreeSms(), actualSmsAmount);
	}
	
	@Test
	void testPayForSmsNotEnoughBalanceException() {
		NotEnoughBalanceException exception = assertThrows(NotEnoughBalanceException.class, () ->{ for (int i = 0; i <200; i++) phone.payForSms(); } );
		assertEquals("Your balance or amount of free sms is not enough to complete this transaction!", exception.getMessage());
	}

}
