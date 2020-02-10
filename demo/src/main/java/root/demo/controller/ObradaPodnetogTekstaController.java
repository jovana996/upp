package root.demo.controller;

import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.demo.model.FormFieldsDto;
import root.demo.services.UserService;

@Controller
@RequestMapping("/text")
public class ObradaPodnetogTekstaController {
	@Autowired
	IdentityService identityService;

	@Autowired
	private RuntimeService runtimeService;

	
	@Autowired
	TaskService taskService;

	@Autowired
	FormService formService;
	
	
	@Autowired
	UserService userService;
	
	@GetMapping(path = "/get", produces = "application/json")
	public @ResponseBody FormFieldsDto startProcess() {
		
		System.out.println("pocinje proces obrade podnetog texta ");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("obradaPodnetogTeksta");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		addInitUsers();
		return new FormFieldsDto(task.getId(), pi.getId(), properties, task.getTaskDefinitionKey());
	}
	
	private void addInitUsers() {
		List<root.demo.entities.User> allUsers = userService.findAll();
			if(allUsers.isEmpty() ) {
			for(root.demo.entities.User u : allUsers) {
				List<User> users = identityService.createUserQuery().userIdIn(u.getEmail()).list();
				if(users.isEmpty()) {
				User user = identityService.newUser(u.getUsername());
				user.setEmail(u.getEmail());
				user.setFirstName(u.getFirstName());
				user.setLastName(u.getLastName());
				user.setPassword(u.getPassword());
				identityService.saveUser(user);
				}
			}
		
		}
		
	}
}
