package root.demo.services;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMailService implements JavaDelegate{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String sendTo = execution.getVariable("email").toString();
		String subject = "Potvrda registracije";
		String message =  "Pozdrav i dobrodosli! Potvrdite vasu registraciju"
				+ " klikom na link  http://localhost:4200/activateAccount";
		
		this.sendMessage(sendTo, subject,message);		
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
