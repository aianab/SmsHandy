package de.whz.gdp2.g8.smshandy.model;

public class Provider {
	public Provider() {
	}
	
	public boolean send(Message message) {
		return true;
	}
	
	public void register(SmsHandy phone) {
		
	}
	
	public void deposit(String phoneNumber, int balance) {
		
	}
	
	public int getCreditForSmsHandy(String phoneNumber) {
		return 0;
	}
	
	private boolean canSendTo(String phoneNumber) {
		return true;
	}
	
	private static Provider findProviderFor(String phoneNumber) {
		return null;
	}
}
