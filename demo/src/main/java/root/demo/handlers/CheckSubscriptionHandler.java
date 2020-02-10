package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.repositories.SubscriptionRepository;
import root.demo.services.MagazineService;
import root.demo.services.SubscriptionService;

@Service
public class CheckSubscriptionHandler implements JavaDelegate {

	@Autowired
	MagazineService magazineService;
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@Autowired
	RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
	Long magazineId = (Long) execution.getVariable("magazine");
	Boolean isPaid = subscriptionService.isPaidSubscription(1l, magazineId);
	execution.setVariable("aktivnaClanarina", isPaid);
	System.out.println(magazineId);

		System.out.println("CheckSubscriptionHandler");
	}



}
