package root.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Magazine;
import root.demo.model.MagazineDTO;
import root.demo.repositories.MagazineRepository;

@Service
public class MagazineService {
	
	@Autowired
	MagazineRepository magazineRepository;
	
	public Long saveMagazine(HashMap<String, Object> map) {
		Magazine magazine = new Magazine();
		magazine.setActive(false);
		magazine.setISSNNumber(Long.parseLong(map.get("ISSNbroj").toString()));
		magazine.setOpenAccess(Boolean.parseBoolean(map.get("openAccess").toString()));
		magazine.setName(map.get("nazivCasopisa").toString());
		Magazine savedMagazine = magazineRepository.save(magazine);
		if(savedMagazine != null) {
		return savedMagazine.getId();
		}
		return null;
	}
	public Magazine getById(Long id) {
	
		return magazineRepository.findById(id).orElse(null);
	
	}
	public Magazine saveExistingMagazine(Magazine magazine) {
		
		return magazineRepository.save(magazine);
	
	}
	public List<Magazine> getAll() {
		
		return magazineRepository.findAll();
	
	}
public List<MagazineDTO> getAllDtos() {
		List<Magazine> all = magazineRepository.findAll();
		
		List<MagazineDTO> ret = new ArrayList<MagazineDTO>();
		for(Magazine m : all) {
			MagazineDTO dto = new MagazineDTO(m);
			ret.add(dto);
		}
		
		return ret;
	
	}
}
