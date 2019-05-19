package de.whz.gdp2.g8.smshandy.model;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;

/**
 * Klasse PrepaidSmsHandy. 
 * Ein Handy, das über ein beim Provider verwaltetes Guthaben verfügt und
 * dessen SMS-Versand über dieses Guthaben abgerechnet wird.
 */
public class PrepaidSmsHandy extends SmsHandy {
	private static final int COST_PER_SMS = 10;
	
	/**
	 * Konstruktor zum Erstellen eines neuen PrepaidHandy
	 * @param number - die Handynummer
	 * @param provider - die Providerinstanz
	 * @throws NumberExistsException 
	 */
	public PrepaidSmsHandy (String number, Provider provider) throws NumberExistsException {
		super(number, provider);
	}


	/**
	 * Prüft, ob das Guthaben noch für das Versenden einer SMS reicht.
	 * @return boolean ist das Guthaben noch ausreichend?
	 */
	@Override
	public boolean canSendSms() {
		return provider.getCreditForSmsHandy(getNumber()) >= COST_PER_SMS;
	}

	@Override
	public void payForSms() throws NotEnoughBalanceException {
		if(!canSendSms()) {
			throw new NotEnoughBalanceException();
		}
		deposit(COST_PER_SMS*(-1));
	}
	
	/**
	 * Lädt das Guthaben fuer das SmsHandy-Objekt auf.
	 * @param amount - Menge, um die Aufgeladen werden soll
	 */
	public void deposit(int amount) {
		provider.deposit(getNumber(), amount);
	}
}