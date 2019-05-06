package de.whz.gdp2.g8.smshandy.model;

public class TariffPlanSmsHandy extends SmsHandy {
	
private int remainingFreeSms = 100;

	public TariffPlanSmsHandy(String number, Provider provider) {
		super(number, provider);
	}

	public boolean canSendSms() {
		return true;
	}

	public void payForSms() {
	
	}

	public int getRemainingFreeSms() {
		return remainingFreeSms;
	}
}
