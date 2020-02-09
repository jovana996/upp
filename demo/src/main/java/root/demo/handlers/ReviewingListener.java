package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewingListener implements TaskListener {

		    @Autowired
		    RuntimeService runtimeService;

		    @Autowired
		    TaskService taskService;

		    @Override
		    public void notify(DelegateTask delegateTask) {

		        DelegateExecution delegateExecution = delegateTask.getExecution();

		        Boolean taskOneSet = (Boolean) runtimeService.getVariable(delegateExecution.getId(), "taskSet");
		        String reviewer1 = runtimeService.getVariable(delegateExecution.getId(), "recezent1").toString();
		        String reviewer2 = runtimeService.getVariable(delegateExecution.getId(), "recezent2").toString();
		        System.out.println(reviewer1 + " " + reviewer2);
		        if(taskOneSet != null && taskOneSet) {
		            delegateTask.setAssignee(reviewer2);
		            runtimeService.setVariable(delegateExecution.getId(), "taskSet", false);
		        } else {
		            delegateTask.setAssignee(reviewer1);
		            runtimeService.setVariable(delegateExecution.getId(), "taskSet", true);
		        }
		    }
}
