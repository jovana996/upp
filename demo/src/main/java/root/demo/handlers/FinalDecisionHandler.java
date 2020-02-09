package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Paper;
import root.demo.entities.ScienceArea;
import root.demo.services.PaperService;

@Service
public class FinalDecisionHandler implements TaskListener{

	   @Autowired
	    RuntimeService runtimeService;
	   
	   @Autowired
	    PaperService paperService;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("FinalDecisionHandler");	
		DelegateExecution execution = delegateTask.getExecution();
		Long paperId = (Long) runtimeService.getVariable(execution.getId(), "paper");
		Paper paper = paperService.getById(paperId);
		String finalDecision = runtimeService.getVariable(execution.getId(), "odluka2").toString();
		System.out.println("Final decision "+finalDecision);
		
		if(finalDecision.equals("prihvati")) {
			paper.setAccepted(true);
		}
		
		paperService.save(paper);
	}


}
