package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import root.demo.entities.Magazine;

@Service
public class CheckIsMagazineOpenAccessHandler implements JavaDelegate {

	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
	Magazine magazine = (Magazine) execution.getVariable("magazine");
		if(magazine != null) {
		execution.setVariable("openAccess", magazine.getOpenAccess());
		}
		System.out.println("CheckIsMagazineOpenAccessHandler");
	}

}
