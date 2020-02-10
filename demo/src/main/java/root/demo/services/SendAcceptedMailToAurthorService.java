package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import root.demo.entities.User;

@Service
public class SendAcceptedMailToAurthorService implements JavaDelegate {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired 
	UserService userService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long userId = (Long) execution.getVariable("user");
		User user = userService.findById(userId);
	
		String subject = "Prijavljen rad - odluka";
		String message = "Rad je prihavcen!";
		this.sendMessage(user.getEmail(), subject, message);
		System.out.println("SendAcceptedMailToAurthorService");
	}
	@Async
	public void sendMessage(String to, String subject, String message) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);

		javaMailSender.send(simpleMailMessage);
	}

}
