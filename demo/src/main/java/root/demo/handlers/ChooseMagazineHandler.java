package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Magazine;
import root.demo.services.MagazineService;

@Service
public class ChooseMagazineHandler implements TaskListener {
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	MagazineService magazineService;

	@Override
	public void notify(DelegateTask delegateTask) {
		try {
			DelegateExecution execution = delegateTask.getExecution();
			String casopis = runtimeService.getVariable(execution.getId(), "casopis").toString();
			Magazine magazine = magazineService.getById(Long.parseLong(casopis));
			execution.setVariable("magazine", magazine);
			System.out.println("caospiss " + magazine.getName());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
