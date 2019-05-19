package de.whz.gdp2.g8.smshandy.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;


public class TariffPlanSmsHandyTest {
	
	@Test
	void testCanSendSms() throws NumberExistsException {
		String sender = "123";
		Provider provider = new Provider();
		TariffPlanSmsHandy phone = new TariffPlanSmsHandy(sender, provider);
		assertEquals(phone.canSendSms(), true);
	}
	
	@Test
	void testCanNotSendSms() throws NumberExistsException {
		String sender = "123";
		Provider provider = new Provider();
		TariffPlanSmsHandy phone = new TariffPlanSmsHandy(sender, provider);
		phone.setRemainingFreeSms1(0);
		assertEquals(phone.canSendSms(), false);
	}
	
	@Test
	void testPayForSms() throws NumberExistsException {
		int actualSmsAmount = 99;
		String sender = "123";
		Provider provider = new Provider();
		TariffPlanSmsHandy phone = new TariffPlanSmsHandy(sender, provider);
		phone.payForSms();
		assertEquals(phone.getRemainingFreeSms(), actualSmsAmount);
	}

}
