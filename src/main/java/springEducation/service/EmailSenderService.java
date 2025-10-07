package springEducation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMessage(String receiverAddress, String messageTheme, String textMessage) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(receiverAddress);
		message.setSubject(messageTheme);
		message.setText(textMessage);

		mailSender.send(message);
	}
}