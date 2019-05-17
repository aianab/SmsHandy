package de.whz.gdp2.g8.smshandy.exception;

public class NotEnoughBalanceException extends Exception {
	
	public NotEnoughBalanceException() {
		super("Your balance or amount of free sms is not enough to complete this transaction!");
	}
}
