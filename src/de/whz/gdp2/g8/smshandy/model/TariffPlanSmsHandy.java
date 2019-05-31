package de.whz.gdp2.g8.smshandy.model;

import java.util.List;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;

/**
 * Klasse TariffPlanSmsHandy. Ein Vertragshandy, das �ber eine bestimmte Menge
 * an Frei-SMS verf�gt. In einer sp�teren Version k�nnten diese nach einer
 * bestimmten Zeit wieder zur�ckgesetzt werden. Dies wird vorerst noch nicht
 * ber�cksichtigt.
 */
public class TariffPlanSmsHandy extends SmsHandy {

	private int remainingFreeSms = 100;

	/**
	 * Konstruktor zum Erstellen eines neuen TariffPlanHandy
	 * 
	 * @param number   - die Handynummer
	 * @param provider - die Providerinstanz
	 * @throws NumberExistsException
	 * @throws ProviderNotGivenException
	 * @throws NumberNotExistException
	 */
	public TariffPlanSmsHandy(String number, Provider provider)
			throws NumberExistsException, NumberNotExistException, ProviderNotGivenException {
		super(number, provider);
	}

	public void setRemainingFreeSms(int remainingFreeSms) {
		this.remainingFreeSms = remainingFreeSms;
	}

	/**
	 * Pr�ft, ob Frei-SMS noch zum Senden ausreichen.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean canSendSms() {
		return remainingFreeSms > 0;
	}

	@Override
	public void payForSms() throws NotEnoughBalanceException {
		if (!canSendSms()) {
			throw new NotEnoughBalanceException();
		}
		remainingFreeSms--;
	}

	/**
	 * Liefert Anzahl der verbliebenen Frei-SMS.
	 * 
	 * @return int
	 */
	public int getRemainingFreeSms() {
		return remainingFreeSms;
	}
}
