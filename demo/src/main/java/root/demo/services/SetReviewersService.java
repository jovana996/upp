package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SetReviewersService implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
	/*	var users = ["john", "mary"];
		var ArrayList = Java.type("java.util.ArrayList");
		var rec = new ArrayList(users);
		execution.setVariable("listaRecezenata", rec);*/
		
	}

}
