package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMailToEditorAndAuthorService implements JavaDelegate {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String sendToEditor = execution.getVariable("glavniUrednik").toString();
		//TODO : send to author
		String subject = "Prijavljen novi rad";
		String message = "Prijavljen je novi rad za obradu!";

		this.sendMessage(sendToEditor, subject, message);
		System.out.println("SendMailToEditorAndAuthorService");
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
