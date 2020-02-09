package root.demo.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.ScienceArea;
import root.demo.entities.User;
import root.demo.enums.Role;
import root.demo.services.ScienceAreaService;
import root.demo.services.UserService;

@Service
public class ChooseReviewersSelectHandler implements TaskListener {
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	ScienceAreaService scienceAreaService;

	@Autowired
	UserService userService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		try {
			DelegateExecution execution = delegateTask.getExecution();
			List<User> reviewers  = new ArrayList<User>();
			reviewers= userService.findByRole(Role.REVIEWER);
	
			TaskFormData taskFormData = execution.getProcessEngineServices().getFormService()
					.getTaskFormData(delegateTask.getId());
			List<FormField> formFields = taskFormData.getFormFields();
			if (formFields != null) {
				for (FormField field : formFields) {
					 if( field.getId().equals("recezent1") || field.getId().equals("recezent2")){
			    	   Map<String, String> values = (Map<String, String>) field.getType().getInformation("values");
			           for(root.demo.entities.User user  :  reviewers){
			               values.put(user.getUsername(),user.getFirstName()+" "+user.getLastName());
			           }
			       }
				}
			}
			   
		} catch (Exception e) {
		}
	}

}
