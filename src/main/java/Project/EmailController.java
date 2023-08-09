package Project;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {
	private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	EmailService emailService;

	@GetMapping(value = "/simple-email/")
	public @ResponseBody ResponseEntity<String> sendSimpleEmail(@RequestParam String email, String subjectofMail, String MatterOfTheMail) {

		try {
			emailService.sendSimpleEmail(email, subjectofMail, MatterOfTheMail);
		} catch (MailException mailException) {
			LOG.error("Error while sending out email..{}", mailException.getStackTrace());
			LOG.error("Error while sending out email..{}", mailException.fillInStackTrace());
			return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
	}

}
