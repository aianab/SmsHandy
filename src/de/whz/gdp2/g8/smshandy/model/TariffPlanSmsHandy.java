package de.whz.gdp2.g8.smshandy.model;

import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;

/**
 * Klasse TariffPlanSmsHandy. Ein Vertragshandy, das über eine bestimmte Menge an Frei-SMS verfügt. In einer späteren Version könnten diese nach einer bestimmten Zeit wieder zurückgesetzt werden. Dies wird vorerst noch nicht berücksichtigt.
 */
public class TariffPlanSmsHandy extends SmsHandy {

private int remainingFreeSms = 100;

	/**
	 * Konstruktor zum Erstellen eines neuen TariffPlanHandy
	 * @param number - die Handynummer
	 * @param provider - die Providerinstanz
	 * @throws NumberExistsException 
	 */
	public TariffPlanSmsHandy(String number, Provider provider) throws NumberExistsException {
		super(number, provider);
	}

	/**
	 * Prüft, ob Frei-SMS noch zum Senden ausreichen.
	 * @return boolean 
	 */
	@Override
	public boolean canSendSms() {
		return remainingFreeSms > 0;
	}

	
	@Override
	public void payForSms() {
		remainingFreeSms--;
	}

	/**
	 * Liefert Anzahl der verbliebenen Frei-SMS.
	 * @return int
	 */
	public int getRemainingFreeSms() {
		return remainingFreeSms;
	}
}
