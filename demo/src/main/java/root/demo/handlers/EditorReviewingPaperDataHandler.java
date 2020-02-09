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
public class EditorReviewingPaperDataHandler implements TaskListener {
	
	@Autowired
	RuntimeService runtimeService;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("EditorReviewingPaperDataHandler");

			DelegateExecution delegateExecution = delegateTask.getExecution();
			Paper paper = (Paper) runtimeService.getVariable(delegateExecution.getId(), "paper");
			boolean isThemeValid = (boolean) runtimeService.getVariable(delegateExecution.getId(), "radRelevantan");

			if (isThemeValid) {
				paper.setState(PaperState.STRUCTURE_REVISION);
			} else {
				paper.setAccepted(false);
			}
		
		delegateExecution.removeVariable("paper");
		delegateExecution.setVariable("paper", paper);
	}
}
