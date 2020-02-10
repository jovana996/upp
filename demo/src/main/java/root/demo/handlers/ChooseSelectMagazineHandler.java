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

import root.demo.entities.Magazine;
import root.demo.entities.ScienceArea;
import root.demo.services.MagazineService;
import root.demo.services.ScienceAreaService;

@Service
public class ChooseSelectMagazineHandler implements TaskListener {
	
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	MagazineService magazineService;

	@Override
	public void notify(DelegateTask delegateTask) {
		try {
			DelegateExecution execution = delegateTask.getExecution();
			List<Magazine> magazines = new ArrayList<Magazine>();
			magazines = magazineService.getAll();
			TaskFormData taskFormData = execution.getProcessEngineServices().getFormService()
					.getTaskFormData(delegateTask.getId());
			List<FormField> formFields = taskFormData.getFormFields();
			if (formFields != null) {
				for (FormField field : formFields) {
					
					if (field.getId().equals("casopis")) {
						Map<String, String> values = (Map<String, String>) field.getType().getInformation("values");
						for (Magazine m : magazines) {
							System.out.println(m.getName());
							values.put(m.getId().toString(), m.getName());
						}
					}
				}
			}
		} catch (Exception e) {
		}
	}
}