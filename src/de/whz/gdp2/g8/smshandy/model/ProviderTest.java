package de.whz.gdp2.g8.smshandy.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.whz.gdp2.g8.smshandy.exception.CantSendException;
import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;

public class ProviderTest {

	private Provider provider1;
	private Provider provider2;
	private SmsHandy phone1;
	private SmsHandy phone2;

	@Before
	public void init() throws Exception {
		this.provider1 = new Provider();
		this.provider2 = new Provider();

		this.phone1 = new PrepaidSmsHandy("123", provider1);
		this.phone2 = new TariffPlanSmsHandy("456", this.provider2);
	}

	@Test
	public void send() throws Exception {
		phone1.sendSms(phone2.getNumber(), "Hello");
		assertEquals(90, provider1.getCreditForSmsHandy(phone1.getNumber()));
	}

	@Test(expected = CantSendException.class)
	public void sendWithWrongNumber() throws Exception {
		phone1.sendSms("woeij", "Hello");
	}

	@Test(expected = NotEnoughBalanceException.class)
	public void sendWithNoBalance() throws Exception {
		phone1.sendSms(phone2.getNumber(), "Hello");
		phone1.sendSms(phone2.getNumber(), "Hello");
		phone1.sendSms(phone2.getNumber(), "Hello");
		phone1.sendSms(phone2.getNumber(), "Hello");
		phone1.sendSms(phone2.getNumber(), "Hello");

		phone1.sendSms(phone2.getNumber(), "Hello");
		phone1.sendSms(phone2.getNumber(), "Hello");
		phone1.sendSms(phone2.getNumber(), "Hello");
		phone1.sendSms(phone2.getNumber(), "Hello");
		phone1.sendSms(phone2.getNumber(), "Hello");

		phone1.sendSms(phone2.getNumber(), "Hello");
	}

	@Test(expected = NumberExistsException.class)
	public void registerWithTheSame() throws Exception {
		new PrepaidSmsHandy("789", provider1);
		new TariffPlanSmsHandy("789", provider1);
	}
	
	@Test
	public void register() throws Exception {
		new PrepaidSmsHandy("789", provider1);
		new TariffPlanSmsHandy("911", provider1);
	}

	@Test
	public void deposit() throws Exception {
		provider1.deposit(phone1.getNumber(), 100);
		assert provider1.getCreditForSmsHandy(phone1.getNumber()) == 200;
	}
	
	@Test(expected = NullPointerException.class)
	public void depositWithNotExistingNumber() throws Exception {
		provider1.deposit("Nothing", 100);
	}

	@Test
	public void getCreditForSmsHandy() throws Exception {
		phone1.sendSms("456", "Hello");
		assert provider1.getCreditForSmsHandy(phone1.getNumber()) == 90;
	}
	
	@Test(expected = NumberNotGivenException.class)
	public void getCreditForSmsHandyWithNotExistingNumber() throws Exception {
		provider1.getCreditForSmsHandy("nothing");
	}

}
