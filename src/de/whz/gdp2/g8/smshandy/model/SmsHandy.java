package de.whz.gdp2.g8.smshandy.model;

import java.util.Date;
import java.util.List;


public abstract class SmsHandy {
	private String number;
	private List<Message> received;
	private List<Message> sent;
	
	private List<Message> sent;
	
	private List<Message> received;
	
	protected Provider provider;
	
	public SmsHandy(String number, Provider provider) {
		this.number = number;
		this.provider = provider;
	}
	
	public void sendSms(String to, String content) {
		Message message = new Message();
		message.setFrom(number);
		message.setTo(to);
		message.setContent(content);
		message.setDate(new Date());
		provider.send(message);
		sent.add(message);
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
		received.forEach(System.out::println);	
	}
	
	public void listSent() {
		sent.forEach(System.out::println);
	}
	
	public void receiveSms(Message message) {
<<<<<<< HEAD
	
=======
		received.add(message);
>>>>>>> 75e83e9420f0bd294fc58fd90560d21fd53a323b
	}
	
}
