package de.whz.gdp2.g8.smshandy.model;

public class PrepaidSmsHandy extends SmsHandy {
	private static final int COST_PER_SMS = 10;
	
	public PrepaidSmsHandy (String number, Provider provider) {
		super(number, provider);
	}


	@Override
	public boolean canSendSms() {
		return provider.getCreditForSmsHandy(getNumber()) >= COST_PER_SMS;
	}

	@Override
	public void payForSms() {
		deposit(COST_PER_SMS*(-1));
	}
	
	public void deposit(int amount) {
		provider.deposit(getNumber(), amount);
	}
}