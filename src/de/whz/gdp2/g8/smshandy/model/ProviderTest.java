package de.whz.gdp2.g8.smshandy.model;

import org.junit.Before;
import org.junit.Test;

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
		try {
			phone1.sendSms(phone2.getNumber(), "Hello");
			assert provider1.getCreditForSmsHandy(phone1.getNumber()) == 90;
		} catch (Exception e) {
			assert false;
		}
	}

	@Test
	public void register() throws Exception {
		try {
			SmsHandy phone = new PrepaidSmsHandy("789", provider1);
			assert provider1.getCreditForSmsHandy(phone.getNumber()) == 100;
		} catch (Exception e) {
			assert false;
		}
	}

	@Test
	public void deposit() throws Exception {
		provider1.deposit(phone1.getNumber(), 100);
		assert provider1.getCreditForSmsHandy(phone1.getNumber()) == 200;
	}

	@Test
	public void getCreditForSmsHandy() throws Exception {
		phone1.sendSms("456", "Hello");
		assert provider1.getCreditForSmsHandy(phone1.getNumber()) == 90;
	}

}
