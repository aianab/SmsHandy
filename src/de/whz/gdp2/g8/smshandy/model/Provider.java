package de.whz.gdp2.g8.smshandy.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Provider {
	private Map<String, Integer> credits;
	private Set<SmsHandy> phones;
	
	public Provider() {
		credits = new HashMap<>();
		phones = new HashSet<>();
	}
	
	
	public boolean send(Message message) {
		for(SmsHandy phone : phones) {
			if(phone.getNumber().equalsIgnoreCase(message.getTo()) ) {
				phone.receiveSms(message);
				phone.payForSms();
				return true;
			}
		}
		return false;
	}
	
	public void register(SmsHandy phone) {
		phones.add(phone);
		credits.put(phone.getNumber(), 100);
	}
	
	public void deposit(String number, int amount) {
		credits.put(number, credits.get(number) + amount);
	}
	
	public int getCreditForSmsHandy(String number) {
		return credits.get(number);
	}
	
	public Set<SmsHandy> getSmsHandys() {
		return phones;
	}
	
	
	private boolean canSendTo(String number) {
		for(SmsHandy phone :phones) {
			if(phone.getNumber().equalsIgnoreCase(number)) {
				return phone.canSendSms();
			}
		}
		return false;
	}
	
	private static Provider findProviderFor(String number) {
		//ToDO
		return null;
	}
}
