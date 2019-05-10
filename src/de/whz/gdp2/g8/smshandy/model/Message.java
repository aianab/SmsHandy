package de.whz.gdp2.g8.smshandy.model;

import java.util.Date;

public class Message{
	private String content;
	private Date date;
	private String from;
	private String to;
	
	/**Konstruktor ohne Parameter*/
	public Message() {
		
	}
	
	
	/**
	 * Konstruktor mit Parametern
	 * @param from - Absender
	 * @param to - Empfaenger
	 * @param content - Inhalt der Nachricht
	 * @param date - Datum
	 * */
	public Message(String from, String to, String content, Date date ) {
		this.content = content;
		this.from = from;
		this.to = to;
		this.date = date;
	}
	
	/**
	 * Gibt das Erstellungsdatum der Nachricht zurück.
	 * @return java.util.Date Erstellungsdatum der SMS
	 * */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * Setzt das Erstellungsdatum der SMS.
	 * @param date - Neues Datum fuer die SMS
	 * */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/** 
	 * Gibt den Inhalt der Nachricht zurueck. 
	 * @return java.lang.String aktueller Inhalt der SMS
	 * */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Gibt das Erstellungsdatum der Nachricht zurück.
	 * @param content - neuer Inhalt fuer die SMS
	 * */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Gibt den Absender der Nachricht zurueck.
	 * @return java.lang.String aktueller Empfaenger fuer die SMS
	 * */
	public String getTo() {
		return this.to;
	}
	
	/**
	 * Setzt den Empfaenger der Nachricht.
	 * @param to - neuer Empfaenger fuer die SMS
	 * */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * Gibt den Absender der Nachricht zurueck.
	 * @return aktueller Absender der SMS
	 * */
	public String getFrom() {
		return this.from;
	}
	
	/**
	 * Setzt den Absender der Nachricht.
	 * @param from - neuer Absender fuer die SMS
	 * */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * Gibt die vollstaendige Nachricht als String zurueck.
	 * @return java.lang.String formatierter String, mit allen Daten
	 * */
	@Override
	public String toString() {
		return "Message [content=" + content + ", date=" + date + ", from=" + from + ", to=" + to + "]";
	}
}
