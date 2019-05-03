package de.whz.gdp2.g8.smshandy.model;

public abstract class SmsHandy {
	private String number;
	
	protected Provider provider;
	public SmsHandy(String number, Provider provider) {
		this.number = number;
	}
	
	public void sendSms(String to, String content) {
		
	}
	
	public abstract boolean canSendSms();
	
	public abstract void payForSms();
	
	public void sendSmsDirect(SmsHandy peer, String content) { }
	
	public String getNumber() {
		return null;
	}
	
	public Provider getProvider() {
		return null;
	}
	
	public void setProvider(Provider provider) {
		
	}
	
	public void listReceived() {
		
	}
	
	public void listSent() {
		
	}
	
	public void receiveSms(Message message) {
		
	}
}
