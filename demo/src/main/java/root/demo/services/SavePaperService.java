package root.demo.services;

import java.util.UUID;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Paper;
import root.demo.entities.ScienceArea;
import root.demo.enums.PaperState;

@Service
public class SavePaperService implements JavaDelegate {

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	PaperService paperService;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		Long paperId = (Long) delegateExecution.getVariable("paper");
		Paper paper = paperService.getById(paperId);
		System.out.println(paper);
		if(paper != null) {
		paper.setDoi(UUID.randomUUID().toString());
		paper.setAccepted(true);
		paper.setState(PaperState.ACCEPTED);
	/*	ScienceArea sa = (ScienceArea) delegateExecution.getVariable("scienceAreaPaper");
		paper.setScienceArea(sa);
		System.out.println(sa);*/
		Paper savedPaper = paperService.save(paper);
		System.out.println("CUVANJE TEKSTA!");
		System.out.println(savedPaper);
		}
	}
}
