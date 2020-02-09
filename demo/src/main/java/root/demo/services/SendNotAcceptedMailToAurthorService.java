package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendNotAcceptedMailToAurthorService implements JavaDelegate{
	@Autowired
	private JavaMailSender javaMailSender; 
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		//TODO : send to author
		String subject = "Prijavljeni rad - odluka";
		String message = "Prijavljen rad se prihvata, ali je neophodna dorada nagalasena u komentarima!";

		//this.sendMessage("jo@mailinator.com", subject, message);
		System.out.println("SendNotAcceptedMailToAurthorService");
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
