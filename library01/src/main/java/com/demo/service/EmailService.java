package com.demo.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.demo.domain.Email;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(Email email) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				
				helper.setFrom(email.getMailFrom());  //recipient
				helper.setTo(email.getMailTo()); //sender
				helper.setSubject(email.getMailSubject()); // mail title
				helper.setText(email.getMailContent(), true); // mail content
			}
		};
		mailSender.send(preparator);
	}
}
