package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Paper;
import root.demo.enums.PaperState;
import root.demo.services.PaperService;

@Service
public class EditorReviewingPaperDataHandler implements TaskListener {
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	PaperService paperService;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("EditorReviewingPaperDataHandler");

			DelegateExecution delegateExecution = delegateTask.getExecution();
			Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "paper");
			Paper paper = paperService.getById(paperId);
			boolean isThemeValid = (boolean) runtimeService.getVariable(delegateExecution.getId(), "radRelevantan");

			if (isThemeValid) {
				paper.setState(PaperState.STRUCTURE_REVISION);
			} else {
				paper.setAccepted(false);
			}
		
		paperService.save(paper);
	}
}
