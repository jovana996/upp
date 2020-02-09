package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import root.demo.entities.Magazine;

@Service
public class ChooseMagazineEditorHandler  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Magazine magazine = (Magazine) execution.getVariable("magazine");
		if(magazine != null) {
		execution.setVariable("glavniUrednik", magazine.getEditor().getEmail());
		}
		System.out.println("ChooseMagazineEditorHandler");
	}

}
