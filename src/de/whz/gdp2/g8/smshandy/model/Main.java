package de.whz.gdp2.g8.smshandy.model;

import java.util.Date;

import de.whz.gdp2.g8.smshandy.exception.CantSendException;
import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;

public class Main {
	public static void main(String ...args) throws NotEnoughBalanceException, CantSendException, NumberExistsException {
		Provider p = new Provider();
		Provider p2 = new Provider();
		SmsHandy phone1 = new PrepaidSmsHandy("123", p);
		SmsHandy phone2 = new TariffPlanSmsHandy("456", p2);
		new Message("ewfij", "ewoifj", "oiwefj", new Date());
		phone1.sendSms("456", "asdkhd");
		phone2.listReceived();
		phone1.listSent();
		phone1.sendSms("*101#", "");
		phone1.listReceived();
	}
}
;