package de.whz.gdp2.g8.smshandy.model;

public class TariffPlanSmsHandy extends SmsHandy {
	
private int remainingFreeSms = 100;

	public TariffPlanSmsHandy(String number, Provider provider) {
		super(number, provider);
	}

	public boolean canSendSms() {
		return remainingFreeSms > 0;
	}

	public void payForSms() {
		remainingFreeSms--;
	}

	public int getRemainingFreeSms() {
		return remainingFreeSms;
	}
}
