package edu.mum.helpers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	/*
	 * Send HTML mail
	 */
	public void sendNotificationMail(final String recipientName, final String recipientEmail, String emailBody)
			throws MessagingException {

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject("Batch Result");

		// could have CC, BCC, will also take an array of Strings
		message.setTo(recipientEmail);
		message.setText("Execution result", true /* isHtml */);

		// Send email
		this.mailSender.send(mimeMessage);
	}	

}
