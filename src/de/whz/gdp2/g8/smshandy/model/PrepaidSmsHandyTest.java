package de.whz.gdp2.g8.smshandy.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;

class PrepaidSmsHandyTest {

	@Test
	void testPayForSms() throws NumberExistsException, NotEnoughBalanceException {
		Integer actualBalance = 90;
		String sender = "123";
		Provider provider = new Provider();
		SmsHandy phone1 = new PrepaidSmsHandy(sender, provider);
		phone1.payForSms();
		assertEquals(provider.getCredits().get(sender), actualBalance);
	}

	@Test
	void testDeposit() throws NumberExistsException{
		Integer actualBalance = 200;
		String sender = "123";
		Provider provider = new Provider();
		PrepaidSmsHandy phone = new PrepaidSmsHandy(sender, provider);
		phone.deposit(100);
		assertEquals(provider.getCredits().get(sender), actualBalance);
	}
	
	@Test
	void testCanSendSms() throws NumberExistsException{
		String sender = "123";
		Provider provider = new Provider();
		PrepaidSmsHandy phone = new PrepaidSmsHandy(sender, provider);
		provider.getCredits().put(sender, 20);
		assertEquals(phone.canSendSms(), true);
	}
	
	@Test
	void testCanNotSendSms() throws NumberExistsException{
		String sender = "123";
		Provider provider = new Provider();
		PrepaidSmsHandy phone = new PrepaidSmsHandy(sender, provider);
		provider.getCredits().put(sender, 0);
		assertEquals(phone.canSendSms(), false);
	}
}
