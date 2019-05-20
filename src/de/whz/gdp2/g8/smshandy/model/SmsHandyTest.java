package de.whz.gdp2.g8.smshandy.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.whz.gdp2.g8.smshandy.exception.CantSendException;
import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;

class SmsHandyTest {

	String reciever;
	String content;
	Provider provider;
	SmsHandy phone1;
	SmsHandy phone2;
	
	@BeforeEach
	public void init() throws NumberExistsException {
		reciever = "0312";
		content = "Hi";
		provider = new Provider();
		phone1 = new PrepaidSmsHandy("sender", provider);
		phone2 = new PrepaidSmsHandy(reciever, provider);
	}
	@Test
	void testSendSms() throws NotEnoughBalanceException, CantSendException, NumberExistsException {
		phone1.sendSms(reciever, content);
		assertEquals(phone1.getSent().get(0).getTo(), reciever);
	}

	@Test
	void testReceiveSms() throws NumberExistsException, CantSendException {
		Message message1 = new Message("sender1", reciever, "Hi!", new Date());
		Message message2 = new Message("sender2", reciever, "Hi again!", new Date());
		Message message3 = new Message("sender3", reciever, "Bye", new Date());		
		List <Message> actual = new ArrayList();
		
		phone2.receiveSms(message1);
		phone2.receiveSms(message2);
		phone2.receiveSms(message3);
		
		actual.add(message1);
		actual.add(message2);
		actual.add(message3);
		
		assertEquals(phone2.getReceived(), actual);
	}
	
	@Test
	void testSendSmsDirect() throws NotEnoughBalanceException, CantSendException, NumberExistsException {
		phone1.sendSmsDirect(phone2, content);
		assertEquals(phone1.getSent().get(0).getTo(), reciever);
	}
}
