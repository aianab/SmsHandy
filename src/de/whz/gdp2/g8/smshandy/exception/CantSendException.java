package de.whz.gdp2.g8.smshandy.exception;

public class CantSendException extends Exception {
	public CantSendException(){
		super("Sms can't be sent");
	}
}
	
