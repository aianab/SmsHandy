package de.whz.gdp2.g8.smshandy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;

/**
 * Klasse Provider
 */
public class Provider {
	public static List<Provider> providerList = new ArrayList<>();
	public static final String BALANCE_COMMAND = "*101#";
	private String name;
	private Map<String, Integer> credits;
	private Map<String, SmsHandy> phones;

	/** Konstruktor fuer Objekte der Klasse Provider */
	public Provider(String name) {
		this.name = name;
		credits = new HashMap<>();
		phones = new HashMap<>();
		providerList.add(this);
	}
	
	public Map<String, SmsHandy> getPhones(){
		return phones;
	}
	/**
	 * Sendet die SMS an den Empfaenger, wenn dieser bekannt ist
	 * 
	 * @param message - die zu sendente SMS
	 * @return true, wenn SMS gesendet werden konnte
	 * @throws ProviderNotGivenException  
	 * @throws NumberNotExistException 
	 */
	public boolean send(Message message) throws NumberNotGivenException, ProviderNotGivenException, NumberNotExistException  {
		SmsHandy from = findProviderFor(message.getFrom()).phones.get(message.getFrom());
		
		if (from == null) {
			throw new NumberNotGivenException();
		}

		if (message.getTo().equals(BALANCE_COMMAND)) {
			Message m = new Message();
			m.setFrom("Operator");
			m.setTo(message.getFrom());
			m.setDate(new Date());
			m.setContent("Your current balance is " + credits.get(message.getFrom()) + ".");

			from.receiveSms(m);
			return true;
		}
		
		Provider p = findProviderFor(message.getTo());
		
		if(p == null) throw new ProviderNotGivenException();
		
		SmsHandy to = p.phones.get(message.getTo());

		if(to == null) throw new NumberNotGivenException();
		
		to.receiveSms(message);
		return true;
	}

	/**
	 * Registriert ein neues Handy bei diesem Provider
	 * 
	 * @param smsHandy - das neue Handy
	 * @throws NumberExistsException
	 */
	public void register(SmsHandy phone) throws NumberExistsException {
		if (canSendTo(phone.getNumber())) {
			throw new NumberExistsException();
		}
		phones.put(phone.getNumber(), phone);
		credits.put(phone.getNumber(), 100);
	}

	/**
	 * Laedt Guthaben für ein Handy auf. Das ist noetig, weil das Handy sein
	 * Guthaben nicht selbst aendern kann, sondern nur der Provider. Negative
	 * Geldmengen werden hier erlaubt, um ueber diese Funktion auch die Kosten fuer
	 * eine Nachricht abziehen zu koennen. Negative Werte beim haendischen Aufladen
	 * werden in der Klasse SmsHandy verhindert.
	 * 
	 * @param number - Nummer des Telefons
	 * @param amount - Hoehe des Geldbetrages
	 * @throws NumberNotGivenException 
	 */
	public void deposit(String number, int amount) throws NumberNotGivenException {
		if (number == null || number.isEmpty())
			throw new NumberNotGivenException();
		credits.put(number, credits.get(number) + amount);
	}

	/**
	 * Gibt das aktuelle Guthaben des betreffenden Handys zurück
	 * 
	 * @param number - Nummer des gewuenschten Handys
	 * @return aktuelles Guthaben des Handys
	 */
	public int getCreditForSmsHandy(String number) throws NumberNotGivenException {
		if (number == null || number.isEmpty())
			throw new NumberNotGivenException();
		return credits.get(number);
	}

	private boolean canSendTo(String number) {
		return phones.get(number) != null;
	}

	private static Provider findProviderFor(String number) {
		for (Provider provider : providerList) {
			if (provider.canSendTo(number)) {
				return provider;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
