package me.cultura.emem.mail.test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {
	
	public static void main(String[] args) {
		

		int total = 3;
		int disponiveis = 0;
		
		int perc = (disponiveis * 100) / total;//80%  13  6

		System.out.println(perc);
		
		perc = (perc > 10 ? perc / 10 : perc);//transforma a porcentagem em uma escala de 1 a 10
		
		System.out.println(perc);
	}
 
	public static void main2(String[] args) {
 
		final String username = "log4j.smtp@gmail.com";
		final String password = "smtp.log4j";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("log4j.smtp@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("thiagonasper@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}