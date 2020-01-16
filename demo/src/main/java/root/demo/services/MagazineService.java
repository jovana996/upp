package root.demo.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Magazine;
import root.demo.entities.User;
import root.demo.enums.Role;
import root.demo.repositories.MagazineRepository;

@Service
public class MagazineService {
	
	@Autowired
	MagazineRepository magazineRepository;
	
	public Long saveMagazine(HashMap<String, Object> map) {
		Magazine magazine = new Magazine();
		magazine.setActive(false);
		magazine.setISSNNumber(Long.parseLong(map.get("ISSNbroj").toString()));
		magazine.setMembershipFeeAuthor(Long.parseLong(map.get("naplataClanarineAutori").toString()));
		magazine.setMembershipFeeReader(Long.parseLong(map.get("naplataClanarineUrednici").toString()));
		magazine.setName(map.get("nazivCasopisa").toString());
		Magazine savedMagazine = magazineRepository.save(magazine);
		if(savedMagazine != null) {
		return savedMagazine.getId();
		}
		return null;
	}

}
