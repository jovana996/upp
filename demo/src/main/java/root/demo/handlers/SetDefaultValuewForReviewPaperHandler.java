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
import root.demo.services.ScienceAreaService;

@Service
public class SetDefaultValuewForReviewPaperHandler  implements TaskListener {
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	ScienceAreaService scienceAreaService;

	@Override
	public void notify(DelegateTask delegateTask) {
		try {
			DelegateExecution execution = delegateTask.getExecution();
			
			TaskFormData taskFormData = execution.getProcessEngineServices().getFormService()
					.getTaskFormData(delegateTask.getId());
			List<FormField> formFields = taskFormData.getFormFields();
			if (formFields != null) {
				for (FormField field : formFields) {
					if (field.getId().equals("naslov")) {
						Map<String, String> values = (Map<String, String>) field.getType().getInformation("values");
						
							values.put("naslov","naslov");
					
					}
				}
			}
		} catch (Exception e) {
		}
	}

}
