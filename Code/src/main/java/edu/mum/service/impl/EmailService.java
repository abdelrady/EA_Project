package edu.mum.service.impl;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import edu.mum.domain.Order;

@Service("emailService")
public class EmailService {
    
  @Autowired
    private JavaMailSender mailSender;
 
    
    /* 
     * Send HTML mail  
     */
    public void sendResultNotificationMail(
            final String recipientName, final String recipientEmail, ChunkContext chunkContext) 
            throws MessagingException {
                 
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
        message.setSubject("Batch Result");
 
        // could have CC, BCC, will also take an array of Strings
        message.setTo(recipientEmail);
        //chunkContext.getStepContext().getStepExecutionContext().
        message.setText("Execution result", true /* isHtml */);
   
        // Send email
        this.mailSender.send(mimeMessage);

    }

 
}
