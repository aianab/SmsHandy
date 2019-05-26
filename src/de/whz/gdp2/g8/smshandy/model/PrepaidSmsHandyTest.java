package de.whz.gdp2.g8.smshandy.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;

class PrepaidSmsHandyTest {

	private String sender;
	private Provider provider;
	private PrepaidSmsHandy phone;
	
	@BeforeEach
	public void init() throws NumberExistsException, NumberNotExistException, ProviderNotGivenException {
	sender = "123";
	provider = new Provider();
	phone = new PrepaidSmsHandy(sender, provider);
	}
	
	@Test
	void testPayForSms() throws NumberExistsException, NotEnoughBalanceException, NumberNotGivenException {
		int actualBalance = 90;
		phone.payForSms();
		assertEquals(provider.getCreditForSmsHandy(phone.getNumber()), actualBalance);
	}
	@Test
	void testPayForSmsNotEnoughBalanceException() throws NumberNotGivenException{
		phone.deposit(-100);
		NotEnoughBalanceException exception = assertThrows(NotEnoughBalanceException.class, () -> phone.payForSms());
		assertEquals("Your balance or amount of free sms is not enough to complete this transaction!", exception.getMessage());
	}

	@Test
	void testDeposit() throws NumberExistsException, NumberNotGivenException{
		int actualBalance = 200;
		phone.deposit(100);
		assertEquals(provider.getCreditForSmsHandy(phone.getNumber()), actualBalance);
	}
	
	@Test
	void testCanSendSms() throws NumberExistsException{
		assertEquals(phone.canSendSms(), true);
	}
	
	@Test
	void testCanNotSendSms() throws NumberExistsException, NumberNotGivenException{
		provider.deposit(sender, -100);
		assertEquals(phone.canSendSms(), false);
	}
}
