package springEducation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springEducation.service.EmailSenderService;

@Data
@RestController
@RequestMapping("/notification")

@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Message was sent successful"),
		@ApiResponse(responseCode = "400", description = "Message wasn't sent")
})

public class UserRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

	@NonNull
	EmailSenderService emailSenderService;

	@Operation(summary = "Send message - Your account was successful created - on userEmail")
	@PostMapping("/userCreatedEvent/{userEmail}")
	public ResponseEntity<?> notificationCreateUser(@PathVariable("userEmail") String userEmail) {
		try {
			emailSenderService.sendMessage(userEmail, "Hallo - creation", "Your account was successful created");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e) {	return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
	}

	@Operation(summary = "Send message - Your account was deleted - on userEmail")
	@DeleteMapping("/userDeletedEvent/{userEmail}")
	public ResponseEntity<?> notificationDeleteUser(@PathVariable("userEmail") String userEmail) {
		try {
			emailSenderService.sendMessage(userEmail, "Hallo", "Your account was deleted");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e) {	return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
	}
}