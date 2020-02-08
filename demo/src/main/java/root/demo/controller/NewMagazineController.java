package root.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
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

import root.demo.entities.Magazine;
import root.demo.entities.ScienceArea;
import root.demo.entities.User;
import root.demo.model.FormFieldsDto;
import root.demo.model.FormSubmissionDto;
import root.demo.model.MagazineDTO;
import root.demo.services.MagazineService;
import root.demo.services.ScienceAreaService;
import root.demo.services.UserService;

@Controller
@RequestMapping("/magazine")
public class NewMagazineController {
	
	@Autowired
	IdentityService identityService;

	@Autowired
	private RuntimeService runtimeService;

	
	@Autowired
	TaskService taskService;

	@Autowired
	FormService formService;
	
	@Autowired
	MagazineService magazineService;

	@Autowired
	ScienceAreaService scienceAreaService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(path = "/get", produces = "application/json")
	public @ResponseBody FormFieldsDto startProcess() {
		System.out.println("pocinje proces kreiranja novog magazina ");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("NoviCasopis");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		taskService.claim(task.getId(), "demo");
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();

		return new FormFieldsDto(task.getId(), pi.getId(), properties, task.getTaskDefinitionKey());
	}
	
	@PostMapping(path = "/post/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		try {
			formService.submitTaskForm(taskId, map);
			return new ResponseEntity<>(magazineService.saveMagazine(map),HttpStatus.OK);
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
	
	@PostMapping(path = "/scienceArea/{taskId}/{magazineId}", produces = "application/json")
	public @ResponseBody ResponseEntity scienceArea(@RequestBody List<FormSubmissionDto> dto,
			@PathVariable String taskId, @PathVariable Long magazineId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		try {
			Magazine magazine = magazineService.getById(magazineId);
			ScienceArea sa = scienceAreaService.findById(Long.parseLong(map.get("naucnaOblast").toString()));
		root.demo.entities.User user = userService.findById(Long.parseLong(map.get("urednikNaucneOblasti").toString()));
		ScienceArea existingSc = magazine.getScienceAreas().stream().
			    filter(s -> s.getId() == sa.getId()).
			    findFirst().orElse(null);
		User existingE = magazine.getEditorsScienceAreas().stream().
			    filter(e -> e.getId() == user.getId()).
			    findFirst().orElse(null);
		if(existingSc == null) {
			magazine.getScienceAreas().add(sa);
			}
		if(existingE == null) {
			magazine.getEditorsScienceAreas().add(user);
		}
			magazineService.saveExistingMagazine(magazine);
			formService.submitTaskForm(taskId, map);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(path = "/addReviewers/{taskId}/{magazineId}", produces = "application/json")
	public @ResponseBody ResponseEntity addReviewers(@RequestBody List<FormSubmissionDto> dto,
			@PathVariable String taskId, @PathVariable Long magazineId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		try {
			Magazine magazine = magazineService.getById(magazineId);
			
			root.demo.entities.User user = userService.findById(Long.parseLong(map.get("recezent1").toString()));
			root.demo.entities.User user2 = userService.findById(Long.parseLong(map.get("recezent2").toString()));
			User existing1 = magazine.getReviewers().stream().
				    filter(u -> u.getId() == user.getId()).
				    findFirst().orElse(null);
			User existing2 = magazine.getReviewers().stream().
				    filter(u -> u.getId() == user2.getId()).
				    findFirst().orElse(null);
			if(existing1 == null) {
				magazine.getReviewers().add(user);
			}
			if(existing2 == null) {
				magazine.getReviewers().add(user2);
			}
			magazineService.saveExistingMagazine(magazine);
			formService.submitTaskForm(taskId, map);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/validateMagazine/{magazineId}", produces = "application/json")
	public @ResponseBody ResponseEntity<MagazineDTO> validateMagazine(@PathVariable Long magazineId) {
		Magazine m = magazineService.getById(magazineId);
		MagazineDTO magazineDto;
		if(m != null) {
		magazineDto = new MagazineDTO(m);
		return new ResponseEntity<>(magazineDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = "/approveMagazine/{taskId}/{magazineId}", produces = "application/json")
	public @ResponseBody ResponseEntity approveReviewer(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId,@PathVariable Long magazineId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		try {
			Magazine m = magazineService.getById(magazineId);
			m.setActive(Boolean.parseBoolean(map.get("aktivan").toString()));
			formService.submitTaskForm(taskId, map);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = "/chooseMagazine/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity chooseMagazine(@RequestBody List<FormSubmissionDto> dto,
			@PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		try {
			formService.submitTaskForm(taskId, map);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
