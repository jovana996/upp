package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import root.demo.entities.Paper;
import root.demo.entities.ScienceArea;
import root.demo.entities.User;

@Service
public class AssigneEditorScienceAreaService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO SET NA GLAVNOG URENIKA!!
		String urednikNO = "demo";
		ScienceArea sa = (ScienceArea) execution.getVariable("scienceAreaPaper");
		User user = sa.getEditor();
		if (user != null && user.getActivatedAccount() != false) {
			urednikNO = user.getUsername();
		}
		System.out.println("Urednik no " + urednikNO);
		execution.setVariable("urednikNO", urednikNO);

	}

}
