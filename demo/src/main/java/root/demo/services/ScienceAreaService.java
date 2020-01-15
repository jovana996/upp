package root.demo.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.ScienceArea;
import root.demo.repositories.ScienceAreaRepository;

@Service
public class ScienceAreaService {
	
	
	@Autowired
	ScienceAreaRepository scienceAreaRepository;
	
	
	public ScienceArea saveScienceArea(HashMap<String, Object> map) {
		ScienceArea sa = new ScienceArea(map.get("naucnaOblast").toString());
		return scienceAreaRepository.save(sa);
	}

}
