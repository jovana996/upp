package root.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Paper;
import root.demo.repositories.PaperRepository;

@Service
public class PaperService {
	
	@Autowired
	PaperRepository paperRepository;
	
	
	public Paper save(Paper paper) {
		return paperRepository.save(paper);
	}
	public Paper getById(Long id) {
		return paperRepository.getOne(id);
	}
}
