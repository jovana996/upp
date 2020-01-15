package root.demo.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.ScienceArea;
import root.demo.entities.User;
import root.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	public Long saveUser(HashMap<String, Object> map) {
		User user = new User();
		user.setUsername(map.get("korisnickoIme").toString());
		user.setCity(map.get("grad").toString());
		user.setCountry(map.get("drzava").toString());
		user.setEmail(map.get("email").toString());
		user.setFirstName(map.get("ime").toString());
		user.setLastName(map.get("prezime").toString());
		user.setPassword(map.get("lozinka").toString());
		user.setTitle(map.get("titula").toString());
		user.setReviewer(Boolean.valueOf(map.get("recezent").toString()));
		User savedUser = userRepository.save(user);
	
		if(savedUser != null) {
		return savedUser.getId();
		}
		return null;
	}
	
	public void addNewScienceArea(Long userId, ScienceArea sa) {
		User u = userRepository.getOne(userId);
		u.getSienceAreas().add(sa);
		userRepository.save(u);
		
	}
	public void activateUserAccount(Long userId) {
		User u = userRepository.getOne(userId);
		u.setActivatedAccount(true);
		userRepository.save(u);
	}
	public void approvedReviewer(Long userId) {
		User u = userRepository.getOne(userId);
		u.setApprovedReviewer(true);
		userRepository.save(u);
	}

}
