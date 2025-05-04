package com.myRoomie.Utilities;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailUtil {

	public static boolean sendEmail(String to, String from, String host, String port, String username, String password,
			String subject, String body, List<File> files, boolean isHtml) {

		// Get system properties
		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.port", port);
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		// session.setDebug(true);
		Transport transport = null;
		try {
			transport = session.getTransport();

			transport.connect(host, username, password);
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(body);
			if (isHtml) {
				message.setContent(body, "text/html; charset=utf-8");
			}
			if (!CollectionUtils.isEmpty(files)) {
				Multipart multipart = new MimeMultipart();
				MimeBodyPart mimeBodyPart = null;
				DataSource dataSource = null;
				boolean fileAdded = false;
				for (File file : files) {
					if (file.isFile() && file.exists()) {
						fileAdded = true;
						mimeBodyPart = new MimeBodyPart();
						dataSource = new FileDataSource(file);
						mimeBodyPart.setDataHandler(new DataHandler(dataSource));
						mimeBodyPart.setFileName(file.getName());
						multipart.addBodyPart(mimeBodyPart);
					}
				}
				if (fileAdded)
					message.setContent(multipart);
			}
			log.info("Sending mail to {} ", to);
			// Send message
			transport.sendMessage(message, message.getAllRecipients());
			log.info("Mail sent successfully to {}", to);
		} catch (Exception e) {
			log.error("Error while sending email to {}", to, e);
			return false;
		} finally {
			if (transport != null && transport.isConnected())
				try {
					transport.close();
				} catch (MessagingException e) {
					log.error("Error closing smtp transport session");
				}
		}
		return true;
	}

	public static boolean sendEmails(String[] to, String[] bcc, String[] cc, String from, String host, String port,
			String username, String password, String subject, String body, List<File> files, boolean isHtml) {

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		// properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		// session.setDebug(true);
		Transport transport = null;
		try {
			transport = session.getTransport();

			transport.connect(host, username, password);
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			int toLength = to.length;
			if (toLength > 0) {
				InternetAddress[] toAddress = new InternetAddress[toLength];
				// To get the array of to addresses
				for (int i = 0; i < toLength; i++) {
					toAddress[i] = new InternetAddress(to[i]);
				}
				// Set To: header field of the header.
				message.addRecipients(Message.RecipientType.TO, toAddress);
			}

			int ccLength = cc.length;
			if (ccLength > 0) {
				InternetAddress[] ccAddress = new InternetAddress[ccLength];
				// To get the array of cc addresses
				for (int i = 0; i < ccLength; i++) {
					ccAddress[i] = new InternetAddress(cc[i]);
				}
				// Set cc: header field of the header.
				message.addRecipients(Message.RecipientType.CC, ccAddress);
			}

			int bccLength = bcc.length;
			if (bccLength > 0) {
				InternetAddress[] bccAddress = new InternetAddress[bccLength];
				// To get the array of bccaddresses
				for (int i = 0; i < bccLength; i++) {
					bccAddress[i] = new InternetAddress(bcc[i]);
				}
				// Set bcc: header field of the header.
				message.addRecipients(Message.RecipientType.BCC, bccAddress);
			}

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(body);
			if (isHtml) {
				message.setContent(body, "text/html; charset=utf-8");
			}
			if (!CollectionUtils.isEmpty(files)) {
				Multipart multipart = new MimeMultipart();
				MimeBodyPart mimeBodyPart = null;
				DataSource dataSource = null;
				boolean fileAdded = false;
				for (File file : files) {
					if (file.isFile() && file.exists()) {
						fileAdded = true;
						mimeBodyPart = new MimeBodyPart();
						dataSource = new FileDataSource(file);
						mimeBodyPart.setDataHandler(new DataHandler(dataSource));
						mimeBodyPart.setFileName(file.getName());
						multipart.addBodyPart(mimeBodyPart);
					}
				}
				if (fileAdded)
					message.setContent(multipart);
			}
			log.info("Sending mail to {} ");
			// Send message
			transport.sendMessage(message, message.getAllRecipients());
			log.info("Mail sent successfully to {}");
		} catch (Exception e) {
			log.error("Error while sending email to {}", e);
			return false;
		} finally {
			if (transport != null && transport.isConnected())
				try {
					transport.close();
				} catch (MessagingException e) {
					log.error("Error closing smtp transport session");
				}
		}
		return true;
	}

}
