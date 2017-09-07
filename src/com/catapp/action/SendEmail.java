package com.catapp.action;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	// take input of email id,subject,content//
	public static Boolean sendEmail(String pemailAddress,String pSubject,String pBody) {  
		final String senderEmail= "fake.email.2017.00@gmail.com";
		final String password = "Texas-A&M-2017";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.authentication", "plain");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(senderEmail, password);
			}
		});
		
	
	try{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(pemailAddress));
		message.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(pemailAddress));
		message.setSubject(pSubject);
		message.setText(pBody);
		
		Transport.send(message);
		
		System.out.println("Done");

	} catch(MessagingException e){
		throw new RuntimeException(e);
	}
	return null;
	
	}
	/*public static void main (String [] args){
		sendEmail("");
	}*/
}