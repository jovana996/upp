package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;

import root.demo.entities.Magazine;
import root.demo.entities.Paper;
import root.demo.enums.PaperState;

public class FinalDecisionHandler implements TaskListener{

	   @Autowired
	    RuntimeService runtimeService;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("FinalDecisionHandler");	
		DelegateExecution execution = delegateTask.getExecution();
		//Paper paper = (Paper) runtimeService.getVariable(execution.getId(), "paper");
		String finalDecision = runtimeService.getVariable(execution.getId(), "odluka2").toString();
		System.out.println("Final decision "+finalDecision);
		
		if(finalDecision.equals("prihvati")) {
			//paper.setAccepted(true);
		}
		execution.removeVariable("paper");
		//execution.setVariable("paper", paper);
	}


}
