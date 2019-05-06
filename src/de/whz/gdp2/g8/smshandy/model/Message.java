package de.whz.gdp2.g8.smshandy.model;

import java.util.Date;

public class Message{
	private String content;
	private Date date;
	private String from;
	private String to;
	
	public Message() {
		
	}
	
	
	
	public Message(String from, String to, String content, Date date ) {
		this.content = content;
		this.from = from;
		this.to = to;
		this.date = date;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTo() {
		return this.to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getFrom() {
		return this.from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	@Override
	public String toString() {
		return "Message [content=" + content + ", date=" + date + ", from=" + from + ", to=" + to + "]";
	}
}
