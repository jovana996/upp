package root.demo.controller;


import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.demo.entities.User;
import root.demo.model.CredentialsDto;
import root.demo.services.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	IdentityService identityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaskService taskService;

	@PostMapping(path = "/login", produces = "application/json")
	public @ResponseBody ResponseEntity login(@RequestBody CredentialsDto credentials ) {
			try {
				System.out.println(credentials.getUsername() + " " + credentials.getPassword());
				User user = userService.findByUsernanmeAndPassword(credentials.getUsername(), credentials.getPassword());
				if(user != null) {
					System.out.println(user);
					return new ResponseEntity<>(user, HttpStatus.OK);
				}
				return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
				
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
