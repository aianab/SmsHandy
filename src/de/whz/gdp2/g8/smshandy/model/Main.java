package de.whz.gdp2.g8.smshandy.model;

public class Main {
	public static void main(String ...args) {
		Provider p = new Provider();
		SmsHandy phone1 = new PrepaidSmsHandy("123", p);
		SmsHandy phone2 = new TariffPlanSmsHandy("456", p);
		p.register(phone1);
		p.register(phone2);
		
		phone1.sendSms("456", "Fuck you");
		phone2.listReceived();
		phone1.listSent();
		phone1.sendSms("*101#", "");
		phone1.listReceived();
	}
}
