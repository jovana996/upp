package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Paper;
import root.demo.enums.PaperState;

@Service
public class EditorReviewingPaperHandler implements TaskListener{

    @Autowired
    RuntimeService runtimeService;
    
	@Override
	public void notify(DelegateTask delegateTask) {
		
	DelegateExecution delegateExecution = delegateTask.getExecution();
	Paper paper = (Paper) runtimeService.getVariable(delegateExecution.getId(), "paper");

    boolean decision = (boolean) runtimeService.getVariable(delegateExecution.getId(), "radDobroFormatiran");

       if(decision) {
           paper.setState(PaperState.REVIEWER_PROPOSAL);
       } else {
           paper.setState(PaperState.APPLICATION_CORRECTION);
       }
       
   	delegateExecution.removeVariable("paper");
	delegateExecution.setVariable("paper", paper);
	}

}
