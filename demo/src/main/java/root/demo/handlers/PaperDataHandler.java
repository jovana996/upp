package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Magazine;
import root.demo.entities.Paper;
import root.demo.entities.ScienceArea;
import root.demo.services.ScienceAreaService;

@Service
public class PaperDataHandler implements TaskListener {

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	ScienceAreaService scienceAreaService;

	@Override
	public void notify(DelegateTask delegateTask) {
		DelegateExecution execution = delegateTask.getExecution();
		String title = runtimeService.getVariable(execution.getId(), "naslov").toString();
		String coAuthorName = runtimeService.getVariable(execution.getId(), "naslov").toString();
		String coAuthorEmail = runtimeService.getVariable(execution.getId(), "naslov").toString();
		String coAuthorAddress = runtimeService.getVariable(execution.getId(), "naslov").toString();
		String keyWords = runtimeService.getVariable(execution.getId(), "naslov").toString();
		String paperAbstract = runtimeService.getVariable(execution.getId(), "naslov").toString();
		String scienceArea = runtimeService.getVariable(execution.getId(), "naucnaOblast").toString();
		String paperText = runtimeService.getVariable(execution.getId(), "naucniRad").toString();

		ScienceArea sa = scienceAreaService.findById(Long.parseLong(scienceArea));
		Paper paper = new Paper(title, coAuthorName, coAuthorEmail, coAuthorAddress, keyWords, paperAbstract, sa, paperText);
		
		execution.setVariable("paper", paper);
		execution.setVariable("scienceAreaPaper", sa);
		System.out.println("paper " + paper.getTitle());

		System.out.println(sa);
		System.out.println("PaperDataHandler");

	}

}
