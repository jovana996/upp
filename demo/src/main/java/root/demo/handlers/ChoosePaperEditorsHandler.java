package root.demo.handlers;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoosePaperEditorsHandler implements TaskListener{

    @Autowired
    RuntimeService runtimeService;
	
	@Autowired
	IdentityService identityService;
	@Override
	public void notify(DelegateTask delegateTask) {
		  List<User> users = identityService.createUserQuery().userIdIn("pera", "mika", "zika").list();
			
		  users = identityService.createUserQuery().list();
		  DelegateExecution execution = delegateTask.getExecution();
	      //  String naslov = runtimeService.getVariable(delegateExecution.getId(), "naslov").toString();

		 execution.setVariable("listaRecezenata", users);
	    	System.out.println("ChoosePaperEditorsHandler");
	
	}

}
