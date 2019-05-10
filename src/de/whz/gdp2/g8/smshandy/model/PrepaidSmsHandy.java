package de.whz.gdp2.g8.smshandy.model;

/**
 * Klasse PrepaidSmsHandy. 
 * Ein Handy, das �ber ein beim Provider verwaltetes Guthaben verf�gt und
 * dessen SMS-Versand �ber dieses Guthaben abgerechnet wird.
 */
public class PrepaidSmsHandy extends SmsHandy {
	private static final int COST_PER_SMS = 10;
	
	/**
	 * Konstruktor zum Erstellen eines neuen PrepaidHandy
	 * @param number - die Handynummer
	 * @param provider - die Providerinstanz
	 */
	public PrepaidSmsHandy (String number, Provider provider) {
		super(number, provider);
	}


	/**
	 * Pr�ft, ob das Guthaben noch f�r das Versenden einer SMS reicht.
	 * @return boolean ist das Guthaben noch ausreichend?
	 */
	@Override
	public boolean canSendSms() {
		return provider.getCreditForSmsHandy(getNumber()) >= COST_PER_SMS;
	}

	@Override
	public void payForSms() {
		deposit(COST_PER_SMS*(-1));
	}
	
	/**
	 * L�dt das Guthaben fuer das SmsHandy-Objekt auf.
	 * @param amount - Menge, um die Aufgeladen werden soll
	 */
	public void deposit(int amount) {
		provider.deposit(getNumber(), amount);
	}
}