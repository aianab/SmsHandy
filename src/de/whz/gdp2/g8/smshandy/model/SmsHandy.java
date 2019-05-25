package de.whz.gdp2.g8.smshandy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import de.whz.gdp2.g8.smshandy.exception.WrongNumberOfRecipient;
import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;

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
	 * @throws NumberNotExistException 
	 * @throws NumberExistsException 
	 * @throws ProviderNotGivenException 
	 */
	public SmsHandy(String number, Provider provider) throws NumberNotExistException, NumberExistsException, ProviderNotGivenException {
		if(number == null) {
			throw new NumberNotExistException();	
		}
		if(provider == null) {
			throw new ProviderNotGivenException();
		}
		this.number = number;
		this.provider = provider;
		this.provider.register(this);
		sent  = new ArrayList<>();
		received = new ArrayList<>();
	}
	
	public List<Message> getSent() {
		return Collections.unmodifiableList(sent);
	}
	
	public List<Message> getReceived() {
		return Collections.unmodifiableList(received);
	}
	
	/**
	 * Schickt eine SMS ueber den Provider an den Empfaenger.
	 * @param to - der Empfaenger der SMS
	 * @param content - der Inhalt der SMS
	 * @throws NotEnoughBalanceException 
	 * @throws WrongNumberOfRecipient 
	 * @throws NumberNotGivenException 
	 * @throws ProviderNotGivenException 
	 */
	public void sendSms(String to, String content) throws NotEnoughBalanceException, WrongNumberOfRecipient, NumberNotGivenException, ProviderNotGivenException {
		if(to == null) {
			throw new NumberNotGivenException();
		}
		Message message = new Message();
		message.setContent(content);
		message.setFrom(this.number);
		message.setTo(to);
		message.setDate(new Date());
		provider.send(message);
		sent.add(message);
		payForSms();
	}
	
	/**
	 * Abstrakte Methode zur Prüfung, ob der Versand der SMS noch bezahlt werden kann.
	 * @return ist der Versand der SMS noch möglich?
	 */
	public abstract boolean canSendSms();
	
	/**
	 * Abstrakte Methode zum Bezahlen des SMS-Versand.
	 * @throws NumberNotGivenException 
	 */
	public abstract void payForSms() throws NotEnoughBalanceException, NumberNotGivenException;
	
	/**
	 * Schickt eine SMS ohne den Provider an den Empfaenger
	 * @param peer - das empfangende Handy
	 * @param content - der Inhalt der SMS
	 * @throws WrongNumberOfRecipient 
	 * @throws NotEnoughBalanceException 
	 * @throws NumberNotGivenException 
	 */
	public void sendSmsDirect(SmsHandy peer, String content) throws NotEnoughBalanceException, NumberNotGivenException, WrongNumberOfRecipient { 
		if(peer == null) {
			throw new NumberNotGivenException();
		}
		Message message = new Message();
		message.setContent(content);
		message.setFrom(this.getNumber());
		message.setTo(peer.getNumber());
		message.setDate(new Date());
		peer.receiveSms(message);
		sent.add(message);
		payForSms();
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
		getSent().forEach(System.out::println);
		System.out.println("---------------------------------");
	}
	
	/**
	 * Empfaengt eine SMS und speichert diese in den empfangenen SMS
	 * @param message - das Message-Objekt, welches an das zweite Handy gesendet werden soll
	 * @throws WrongNumberOfRecipient 
	 */
	public void receiveSms(Message message) throws WrongNumberOfRecipient {
		if(message.getTo() != this.number) {
			throw new WrongNumberOfRecipient();
		}
		received.add(message);
	}
	
}
