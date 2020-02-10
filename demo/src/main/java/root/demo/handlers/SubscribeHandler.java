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
public class SubscribeHandler implements TaskListener {
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	MagazineService magazineService;

	@Override
	public void notify(DelegateTask delegateTask) {
		try {
			DelegateExecution execution = delegateTask.getExecution();
			Long magazineId = (Long) execution.getVariable("magazine");
			Long userId = (Long) execution.getVariable("user");
			
			execution.setVariable("aktivnaClanarina", true);
			System.out.println(magazineId);
			System.out.println("SubscribeHandler");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
