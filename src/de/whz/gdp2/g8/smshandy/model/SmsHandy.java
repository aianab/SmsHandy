package de.whz.gdp2.g8.smshandy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.whz.gdp2.g8.smshandy.exception.CantSendException;
import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;

/**
 * Abstrakte Basisklasse SmsHandy.
 */
public abstract class SmsHandy {
	private String number;
	
	private List<Message> sent;
	
	private List<Message> received;
	
	protected Provider provider;
	
	/**
	 * Konstruktor fuer Objekte der Klasse SmsHandy
	 * @param number - die Handynummer
	 * @param provider - die Providerinstanz
	 */
	public SmsHandy(String number, Provider provider) {
		if(number == null || provider == null) {
			throw new NullPointerException("Number or Provider is not given");
		}
		this.number = number;
		this.provider = provider;
		sent = new ArrayList<>();
		received = new ArrayList<>();
	}
	
	/**
	 * Schickt eine SMS ueber den Provider an den Empfaenger.
	 * @param to - der Empfaenger der SMS
	 * @param content - der Inhalt der SMS
	 */
	public void sendSms(String to, String content) {
		Message message = new Message();
		message.setContent(content);
		message.setFrom(this.number);
		message.setTo(to);
		message.setDate(new Date());
		provider.send(message);
		sent.add(message);
	}
	
	/**
	 * Abstrakte Methode zur Prüfung, ob der Versand der SMS noch bezahlt werden kann.
	 * @return ist der Versand der SMS noch möglich?
	 */
	public abstract boolean canSendSms();
	
	/**
	 * Abstrakte Methode zum Bezahlen des SMS-Versand.
	 */
	public abstract void payForSms() throws NotEnoughBalanceException;
	
	/**
	 * Schickt eine SMS ohne den Provider an den Empfaenger
	 * @param peer - das empfangende Handy
	 * @param content - der Inhalt der SMS
	 */
	public void sendSmsDirect(SmsHandy peer, String content) { 
		if(peer == null) {
			throw new NullPointerException("Reciver's numer is not given");
		}
		Message message = new Message();
		message.setContent(content);
		message.setFrom(this.getNumber());
		message.setTo(peer.getNumber());
		message.setDate(new Date());
		peer.receiveSms(message);
		sent.add(message);
	}
	
	/**
	 * Gibt die Handynummer zurück.
	 * @return die Handynummer
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Gibt den aktuellen Provider zurück.
	 * @return aktueller Provider des Handys
	 */
	public Provider getProvider() {
		return provider;
	}
	
	/**
	 * Setzt den Provider.
	 * @param provider - ProviderInstanz
	 */
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	/**
	 * Gibt eine Liste aller empfangenen SMS auf der Konsole aus.
	 */
	public void listReceived() {
		System.out.println("--------Received messages--------");
		received.forEach(System.out::println);	
		System.out.println("---------------------------------");
	}
	
	/**
	 * Gibt eine Liste aller gesendete SMS auf der Konsole aus.
	 */
	public void listSent() {
		System.out.println("----------Sent messages----------");
		sent.forEach(System.out::println);
		System.out.println("---------------------------------");
	}
	
	/**
	 * Empfaengt eine SMS und speichert diese in den empfangenen SMS
	 * @param message - das Message-Objekt, welches an das zweite Handy gesendet werden soll
	 * @throws CantSendException 
	 */
	public void receiveSms(Message message) throws CantSendException {
		if(message.getTo() != this.number) {
			throw new CantSendException();
		}
		received.add(message);
	}
	
}
