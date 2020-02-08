package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CheckIsMagazineOpenAccessHandler implements JavaDelegate {

	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		execution.setVariable("openAccess", true);
		System.out.println("CheckIsMagazineOpenAccessHandler");
	}

}
