package com.synnex.utils.mailUtil;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEmailThread extends Thread {

	public Logger logger = LoggerFactory.getLogger(this.getClass());
	private String subject;
	private String toemail;
	private String content;

	public SendEmailThread(String subject, String toemail, String content) {
		this.subject = subject;
		this.toemail = toemail;
		this.content = toemail;
	}

	@Override
	public void run() {
		// 发送邮件
		try {
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");// 发送使用的协议
			props.setProperty("mail.host", "smtp.163.com");// 发送服务器的主机地址
			props.setProperty("mail.smtp.auth", "true");// 请求身份验证
			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("hty181818@163.com"));
			message.setRecipients(Message.RecipientType.TO, toemail);
			message.setSubject(subject);

			MimeBodyPart part = new MimeBodyPart();
			part.setContent(content, "text/html;charset=UTF-8");

			MimeMultipart mpart = new MimeMultipart();
			mpart.addBodyPart(part);
			message.setContent(mpart);
			message.saveChanges();

			Transport ts = session.getTransport();
			ts.connect("hty181818@163.com", "wwwcom130");
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (AddressException e) {
//			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (NoSuchProviderException e) {
//			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (MessagingException e) {
//			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}
}
