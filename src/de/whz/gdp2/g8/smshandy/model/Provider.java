package de.whz.gdp2.g8.smshandy.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Provider {
	public static final String BALANCE_COMMAND = "*101#";
	private Map<String, Integer> credits;
	private Map<String, SmsHandy> phones;
	
	public Provider() {
		credits = new HashMap<>();
		phones = new HashMap<>();
	}
	
	
	public boolean send(Message message) {
		if(message.getTo().equals(BALANCE_COMMAND)) {
			Message m = new Message();
			m.setFrom("Operator");
			m.setTo(message.getFrom());
			m.setDate(new Date());
			m.setContent("Your current balance is " + credits.get(message.getFrom()) + ".");
			
			phones.get(message.getFrom()).receiveSms(message);
			return true;
		}
		
		if(phones.get(message.getFrom()) == null || phones.get(message.getTo()) == null) {
			return false;
		}
		
		SmsHandy from = phones.get(message.getFrom());
		SmsHandy to = phones.get(message.getTo());
		
		to.receiveSms(message);
		from.payForSms();
		return true;
	}
	
	public void register(SmsHandy phone) {
		phones.put(phone.getNumber(), phone);
		credits.put(phone.getNumber(), 100);
	}
	
	public void deposit(String number, int amount) {
		credits.put(number, credits.get(number) + amount);
	}
	
	public int getCreditForSmsHandy(String number) {
		return credits.get(number);
	}
	
	
	private boolean canSendTo(String number) {
		return phones.get(number).canSendSms();
	}
	
	private static Provider findProviderFor(String number) {
		//ToDO
		return null;
	}
}
