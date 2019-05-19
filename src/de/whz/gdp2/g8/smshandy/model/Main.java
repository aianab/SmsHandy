package de.whz.gdp2.g8.smshandy.model;

import de.whz.gdp2.g8.smshandy.exception.CantSendException;
import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;

public class Main {
	/**
	 * @param args
	 * @throws NotEnoughBalanceException
	 * @throws CantSendException
	 * @throws NumberExistsException
	 */
	/**
	 * @param args
	 * @throws NotEnoughBalanceException
	 * @throws CantSendException
	 * @throws NumberExistsException
	 */
	public static void main(String ...args) throws NotEnoughBalanceException, CantSendException, NumberExistsException {
		Provider p = new Provider();
//		Provider p2 = new Provider();
		PrepaidSmsHandy phone1 = new PrepaidSmsHandy("123", p);
		new PrepaidSmsHandy("456", p);
		phone1.sendSms("456", "asdkhd");
		phone1.payForSms();
		phone1.sendSms("*101#", "");
		phone1.deposit(100);
//		phone1.sendSmsDirect(phone2, "direct");
//		phone2.sendSms("123", "to Phone1");
//		phone2.listSent();
//		phone1.listSent();
//		phone2.listReceived();
	}
}
;