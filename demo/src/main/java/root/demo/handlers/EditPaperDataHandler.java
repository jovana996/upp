package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Paper;
import root.demo.services.PaperService;

@Service
public class EditPaperDataHandler implements TaskListener{
	
    @Autowired
    RuntimeService runtimeService;
    
	@Autowired
	PaperService paperService;
    
	@Override
	public void notify(DelegateTask delegateTask) {
		 DelegateExecution delegateExecution = delegateTask.getExecution();
		 	Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "paper");
	     	Paper paper = paperService.getById(paperId);
	        String naucniRad = runtimeService.getVariable(delegateExecution.getId(), "naucniRad").toString();
	     	 paper.setPaper(naucniRad);
	     	 paperService.save(paper);
	    	System.out.println("EditPaperDataHandler");
	}

}
