package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SetTimeToReviewService implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable("vremeRecenzije", "PT25M");
		
	}

}
