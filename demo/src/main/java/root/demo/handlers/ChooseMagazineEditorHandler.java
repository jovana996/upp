package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Magazine;
import root.demo.services.MagazineService;

@Service
public class ChooseMagazineEditorHandler  implements JavaDelegate {

	@Autowired
	MagazineService magazineService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long magazineId = (Long) execution.getVariable("magazine");
		Magazine magazine = magazineService.getById(magazineId);
		if(magazine != null) {
		execution.setVariable("glavniUrednik", magazine.getEditor().getEmail());
		}
		System.out.println("ChooseMagazineEditorHandler");
	}

}
