package root.demo.services;

import java.util.UUID;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Paper;

@Service
public class SavePaperService implements JavaDelegate {

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	PaperService paperService;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		Paper paper = (Paper) runtimeService.getVariable(delegateExecution.getId(), "paper");
		paper.setDoi(UUID.randomUUID().toString());
		Paper savedPaper = paperService.save(paper);
		System.out.println("CUVANJE TEKSTA!");
		System.out.println(savedPaper);
	}
}
