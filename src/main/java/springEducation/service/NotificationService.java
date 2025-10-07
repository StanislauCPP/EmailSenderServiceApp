package springEducation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	@Autowired
	EmailSenderService emailSenderService;

	@KafkaListener(groupId = "user-created-events-group", topics = "user-created-events-topic")
	public void listenCreatedUserEvents(@Payload String userEmail, Acknowledgment acknowledgment) {
		emailSenderService.sendMessage(userEmail, "Hallo - creation", "Your account was successful created");

		acknowledgment.acknowledge();
	}

	@KafkaListener(groupId = "user-deleted-events-group", topics = "user-deleted-events-topic")
	public void listenDeletedUserEvents(@Payload String userEmail, Acknowledgment acknowledgment) {
		emailSenderService.sendMessage(userEmail, "Hallo", "Your account was deleted");

		acknowledgment.acknowledge();
	}
}