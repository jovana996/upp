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
public class SendBadStructureMailService implements JavaDelegate{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	UserService userService;
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long userId = (Long) execution.getVariable("user");
		User user = userService.findById(userId);
		String subject = "Prijavljen rad - odluka";
		String message = "Struktura rada ne zadovoljava sve kriterijume."
				+ " Rad je potrebno ispraviti u zadatom roku i ponovo poslati na recenziju."
				+ "U slucaju isteka zadatog roka, rad ce biti odbijen.";
	//	this.sendMessage(user.getEmail(), subject, message);
		System.out.println("SendBadStructureMailService");
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
