package de.whz.gdp2.g8.smshandy.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Klasse Provider
 */
public class Provider {
	public static final String BALANCE_COMMAND = "*101#";
	private Map<String, Integer> credits;
	private Map<String, SmsHandy> phones;
	
	/**Konstruktor fuer Objekte der Klasse Provider*/
	public Provider() {
		credits = new HashMap<>();
		phones = new HashMap<>();
	}
	
	/**
	 * Sendet die SMS an den Empfaenger, wenn dieser bekannt ist
	 * @param message - die zu sendente SMS
	 * @return true, wenn SMS gesendet werden konnte
	 */
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
	
	/**
	 * Registriert ein neues Handy bei diesem Provider
	 * @param smsHandy - das neue Handy
	 	*/
	public void register(SmsHandy phone) {
		phones.put(phone.getNumber(), phone);
		credits.put(phone.getNumber(), 100);
	}
	
	/**
	 * Laedt Guthaben für ein Handy auf. Das ist noetig, 
	 * weil das Handy sein Guthaben nicht selbst aendern kann, 
	 * sondern nur der Provider. 
	 * Negative Geldmengen werden hier erlaubt, 
	 * um ueber diese Funktion auch die Kosten fuer eine Nachricht abziehen zu koennen.
	 *  Negative Werte beim haendischen Aufladen werden in der Klasse SmsHandy verhindert.
	 *  @param number - Nummer des Telefons
	 *  @param amount - Hoehe des Geldbetrages*/
	public void deposit(String number, int amount) {
		credits.put(number, credits.get(number) + amount);
	}
	
	/**
	 * Gibt das aktuelle Guthaben des betreffenden Handys zurück
	 * @param number - Nummer des gewuenschten Handys
	 * @return aktuelles Guthaben des Handys*/
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
