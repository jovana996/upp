package root.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.demo.entities.ScienceArea;
import root.demo.enums.Role;
import root.demo.model.FormFieldsDto;
import root.demo.model.FormSubmissionDto;
import root.demo.services.ScienceAreaService;
import root.demo.services.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
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
	
	@Autowired
	ScienceAreaService scienceAreaService;
	
	@GetMapping(path = "/get", produces = "application/json")
	public @ResponseBody FormFieldsDto get() {
		System.out.println("pocinje proces registracije");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("registracijaKorisnika");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new FormFieldsDto(task.getId(), pi.getId(), properties, task.getTaskDefinitionKey());
	}

	@GetMapping(path = "/activateAccount/{taskId}/{userId}", produces = "application/json")
	public @ResponseBody ResponseEntity activate(@PathVariable String taskId,@PathVariable Long userId) {
		userService.activateUserAccount(userId);
		taskService.complete(taskId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@PostMapping(path = "/post/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		try {
			formService.submitTaskForm(taskId, map);			
			return new ResponseEntity<>(userService.saveUser(map),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping(path = "/get/{processInstanceId}", produces = "application/json")
	public @ResponseBody FormFieldsDto getTask(@PathVariable String processInstanceId) {
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		if(tasks.size() > 0) {
		Task task = tasks.get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		List<root.demo.entities.User> reviewers  = new ArrayList<root.demo.entities.User>();
		List<root.demo.entities.User> editors  = new ArrayList<root.demo.entities.User>();
		
		List<ScienceArea> scienceAreas  = new ArrayList<ScienceArea>();
		reviewers= userService.findByRole(Role.REVIEWER);
		editors= userService.findByRole(Role.EDITOR);
	scienceAreas = scienceAreaService.getAll();
		
		if(properties!=null){
			   for(FormField field : properties){
			       if( field.getId().equals("urednikNaucneOblasti")){
			    	   Map<String, String> values = (Map<String, String>) field.getType().getInformation("values");
			           for(root.demo.entities.User user  :  editors){
			               values.put(user.getId().toString(),user.getFirstName()+" "+user.getLastName());
			           }
			       }
			       if( field.getId().equals("recezent1") || field.getId().equals("recezent2")){
			    	   Map<String, String> values = (Map<String, String>) field.getType().getInformation("values");
			           for(root.demo.entities.User user  :  reviewers){
			               values.put(user.getId().toString(),user.getFirstName()+" "+user.getLastName());
			           }
			       }
			       
			       if( field.getId().equals("naucnaOblast")){
			    	   Map<String, String> values = (Map<String, String>) field.getType().getInformation("values");
			           for(ScienceArea sc  :  scienceAreas){
			        	    values.put(sc.getId().toString(), sc.getName());
			           }
			       }
			   }

		}
		return new FormFieldsDto(task.getId(), processInstanceId, properties, task.getTaskDefinitionKey());
		}
		return null;
		
	}
	
	@PostMapping(path = "/approveReviewer/{taskId}/{userId}", produces = "application/json")
	public @ResponseBody ResponseEntity approveReviewer(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId,@PathVariable Long userId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		try {
			userService.approvedReviewer(userId);
			formService.submitTaskForm(taskId, map);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(path = "/scienceArea/{taskId}/{userId}", produces = "application/json")
	public @ResponseBody ResponseEntity scienceArea(@RequestBody List<FormSubmissionDto> dto,
			@PathVariable String taskId, @PathVariable Long userId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		try {

			ScienceArea sa = scienceAreaService.findById(Long.parseLong(map.get("naucnaOblast").toString()));		
			root.demo.entities.User user = userService.findById(userId);
			ScienceArea existingSc = user.getSienceAreas().stream().
				    filter(s -> s.getId() == sa.getId()).
				    findFirst().orElse(null);
			if(existingSc == null) {
			user.getSienceAreas().add(sa);
			}
			userService.update(user);
			formService.submitTaskForm(taskId, map);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (FormSubmissionDto temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}

		return map;
	}
}
