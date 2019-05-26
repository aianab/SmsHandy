package de.whz.gdp2.g8.smshandy.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.whz.gdp2.g8.smshandy.exception.NotEnoughBalanceException;
import de.whz.gdp2.g8.smshandy.exception.NumberExistsException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotExistException;
import de.whz.gdp2.g8.smshandy.exception.NumberNotGivenException;
import de.whz.gdp2.g8.smshandy.exception.ProviderNotGivenException;

class SmsHandyTest {

	private String sender;
	private String reciever;
	private String content;
	private Provider provider;
	private PrepaidSmsHandy phone1;
	private SmsHandy phone2;
	private SmsHandy nullProviderPhone;

	@BeforeEach
	public void init() throws NumberExistsException, NumberNotExistException, ProviderNotGivenException {
		sender = "sender";
		reciever = "0312";
		content = "Hi";
		provider = new Provider();
		phone1 = new PrepaidSmsHandy(sender, provider);
		phone2 = new PrepaidSmsHandy(reciever, provider);
	}
	
	@Test
	void testSendSms() throws NotEnoughBalanceException, NumberNotGivenException, ProviderNotGivenException, NumberNotExistException {
		phone1.sendSms(reciever, content);
		assertEquals(phone1.getSent().get(0).getTo(), reciever);
	}
	
	@Test
	void testSendSmsNumberNotExistException() {
		NumberNotExistException exception = assertThrows(NumberNotExistException.class, () -> phone1.sendSms("0", content), "Number doesn't exist");
		assertEquals("Number doesn't exist", exception.getMessage());
	}
	
	@Test
	void testSendSmsNotEnoughBalanceException() throws NumberNotGivenException {
		phone1.deposit(-200);
		NotEnoughBalanceException exception = assertThrows(NotEnoughBalanceException.class, () -> phone1.sendSms(reciever, content));
		assertEquals("Your balance or amount of free sms is not enough to complete this transaction!", exception.getMessage());
	}
	
	@Test
	void testSendSmsProviderNotGivenException() throws NumberExistsException, NumberNotExistException {
		ProviderNotGivenException exception = assertThrows(ProviderNotGivenException.class, () -> new PrepaidSmsHandy(sender, null));
		assertEquals("Provider not given", exception.getMessage());
	}

	@Test
	void testReceiveSms() throws NumberNotExistException {
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
	void testReceiveSmsNumberNotExistException(){
		Message m = new Message(sender, sender, "Hi!", new Date());
		NumberNotExistException exception = assertThrows(NumberNotExistException.class, () -> phone2.receiveSms(m));
		assertEquals("Number doesn't exist", exception.getMessage());
	}
	
	@Test
	void testSendSmsDirect() throws NotEnoughBalanceException, NumberNotGivenException, NumberNotExistException {
		phone1.sendSmsDirect(phone2, content);
		assertEquals(phone1.getSent().get(0).getTo(), reciever);
	}
	@Test
	void testSendSmsDirectNotEnoughBalanceException() throws NumberNotGivenException {
		phone1.deposit(-200);
		NotEnoughBalanceException exception = assertThrows(NotEnoughBalanceException.class, () -> phone1.sendSmsDirect(phone2, content));
		assertEquals("Your balance or amount of free sms is not enough to complete this transaction!", exception.getMessage());
	}
	
	@Test
	void testSendSmsDirectNumberNotExistException() {
		NumberNotGivenException exception = assertThrows(NumberNotGivenException.class, () -> phone1.sendSmsDirect(null, content), "Number should be given");
		assertEquals("Number should be given", exception.getMessage());
	}
}
