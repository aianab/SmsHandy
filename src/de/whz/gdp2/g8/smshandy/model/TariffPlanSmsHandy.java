package de.whz.gdp2.g8.smshandy.model;

import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;

/**
 * Klasse TariffPlanSmsHandy. Ein Vertragshandy, das �ber eine bestimmte Menge an Frei-SMS verf�gt. In einer sp�teren Version k�nnten diese nach einer bestimmten Zeit wieder zur�ckgesetzt werden. Dies wird vorerst noch nicht ber�cksichtigt.
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
	 * Pr�ft, ob Frei-SMS noch zum Senden ausreichen.
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
