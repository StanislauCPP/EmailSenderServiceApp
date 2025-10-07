package springEducation.service;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Email receiver is mailhog
 */
@SpringBootTest()
public class EmailSenderServiceTest {

	@Autowired
	EmailSenderService emailService;

	RestTemplate rest = new RestTemplate();

	private final String localhost = "http://localhost:8025";

	@BeforeEach
	void cleanMailHog() { rest.delete(localhost + "/api/v1/messages"); }

	@RequiredArgsConstructor
	@Getter
	class EmailMessageParameters {
		@NonNull String email;
		@NonNull String messageTheme;
		@NonNull String messageText;
	}

	public EmailMessageParameters emailMessageParameters() { return new EmailMessageParameters("email@email.net", "Message theme", "Message text from test"); }

	@Test
	void inputWrongEmailMessage_expectedNullPointerException()	{
		NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
				() -> emailService.sendMessage(null, null, null));

		assertEquals("Cannot invoke \"String.length()\" because \"s\" is null", exception.getMessage());
	}

	@Test
	void inputEmailMessage_expectedSuccessfulSupplyingMailhog() {
		EmailMessageParameters expectedEmailMessageParameters = emailMessageParameters();

		emailService.sendMessage(expectedEmailMessageParameters.getEmail(), expectedEmailMessageParameters.getMessageTheme(), expectedEmailMessageParameters.getMessageText());

		ResponseEntity<String> response = rest.getForEntity(localhost + "/api/v2/messages", String.class);
		String result = response.getBody();

		assertTrue(result.contains(expectedEmailMessageParameters.getEmail()));
		assertTrue(result.contains(expectedEmailMessageParameters.getMessageTheme()));
		assertTrue(result.contains(expectedEmailMessageParameters.getMessageText()));
	}
}