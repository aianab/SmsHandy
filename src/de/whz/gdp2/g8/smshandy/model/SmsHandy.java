package de.whz.gdp2.g8.smshandy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class SmsHandy {
	private String number;
	
	private List<Message> sent;
	
	private List<Message> received;
	
	protected Provider provider;
	
	public SmsHandy(String number, Provider provider) {
		this.number = number;
		this.provider = provider;
		sent = new ArrayList<>();
		received = new ArrayList<>();
	}
	
	public void sendSms(String to, String content) {
		Message message = new Message();
		message.setContent(content);
		message.setFrom(this.number);
		message.setTo(to);
		message.setDate(new Date());
		provider.send(message);
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
		System.out.println("--------Received messages--------");
		received.forEach(System.out::println);	
		System.out.println("---------------------------------");
	}
	
	public void listSent() {
		System.out.println("----------Sent messages----------");
		sent.forEach(System.out::println);
		System.out.println("---------------------------------");
	}
	
	public void receiveSms(Message message) {
		received.add(message);
	}
	
}
