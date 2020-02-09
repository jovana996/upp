package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SaveReviewHandler implements TaskListener{

	   @Autowired
	    RuntimeService runtimeService;

	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution delegateExecution = delegateTask.getExecution();
		//Paper paper = (Paper) runtimeService.getVariable(delegateExecution.getId(), "paper");
		String comment = runtimeService.getVariable(delegateExecution.getId(), "kometarUrednicima").toString();
		String comment2 = runtimeService.getVariable(delegateExecution.getId(), "komentarAutoru").toString();

		System.out.println("SaveReviewService");	
		
	}

}
