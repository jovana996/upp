package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewCommentsAndEditHandler implements TaskListener{

    @Autowired
    RuntimeService runtimeService;
    
	@Override
	public void notify(DelegateTask delegateTask) {
		 DelegateExecution delegateExecution = delegateTask.getExecution();

	      //  String naslov = runtimeService.getVariable(delegateExecution.getId(), "naslov").toString();
		 	
	    	System.out.println("ReviewingPaperHandler");
	
	}
}
