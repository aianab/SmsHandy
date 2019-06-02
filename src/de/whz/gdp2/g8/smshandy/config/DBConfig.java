package de.whz.gdp2.g8.smshandy.config;

import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;
import de.whz.gdp2.g8.smshandy.model.PrepaidSmsHandy;
import de.whz.gdp2.g8.smshandy.model.Provider;
import de.whz.gdp2.g8.smshandy.model.TariffPlanSmsHandy;

public class DBConfig {

    public static void initDB() throws Exception {
        Provider b = new Provider("Beeline");
        PrepaidSmsHandy phone1 = new PrepaidSmsHandy("456", b);

        Provider o = new Provider("O!");
        TariffPlanSmsHandy phone2 = new TariffPlanSmsHandy("123", o);

        Provider m = new Provider("Megacom");
        TariffPlanSmsHandy phone3 = new TariffPlanSmsHandy("789", m);

        phone1.sendSms("123", "Hello from phovider 1 phone");
        phone2.sendSms("456", "Hi");

        phone3.sendSms("456", "What's up?");
        phone2.sendSms("789", "Guten Morgen!");
    }
}
