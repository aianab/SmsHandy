package de.whz.gdp2.g8.smshandy.model;

import java.util.Date;


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
	
	public void sendSmsDirect(SmsHandy peer, String content) { 
		Message message = new Message();
		message.setContent(content);
		message.setFrom(this.getNumber());
		message.setTo(peer.getNumber());
		message.setDate(new Date());
		receiveSms(message);
	}
	
	public String getNumber() {
		return number;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public void listReceived() {
		
	}
	
	public void listSent() {
		
	}
	
	public void receiveSms(Message message) {
		
	}
}
