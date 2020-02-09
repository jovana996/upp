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
public class ChoosePaperEditorsHandler implements TaskListener {

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	IdentityService identityService;

	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution execution = delegateTask.getExecution();
		String recezent1 = runtimeService.getVariable(execution.getId(), "recezent1").toString();
		String recezent2 = runtimeService.getVariable(execution.getId(), "recezent2").toString();

		List<User> users = identityService.createUserQuery().userIdIn(recezent1, recezent2).list();
		users = identityService.createUserQuery().list();
		execution.setVariable("listaRecezenata", users);
		System.out.println("ChoosePaperEditorsHandler");

	}

}
