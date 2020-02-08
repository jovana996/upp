package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterPaperDataHandler implements TaskListener{
	
    @Autowired
    RuntimeService runtimeService;
    
	@Override
	public void notify(DelegateTask delegateTask) {
		 DelegateExecution delegateExecution = delegateTask.getExecution();

	        String naslov = runtimeService.getVariable(delegateExecution.getId(), "naslov").toString();
	        String koautorIme = runtimeService.getVariable(delegateExecution.getId(), "koautorIme").toString();
		 	
	        String koatutorEmail = runtimeService.getVariable(delegateExecution.getId(), "koatutorEmail").toString();
	        String koautorAdresa = runtimeService.getVariable(delegateExecution.getId(), "koautorAdresa").toString();
	        String kljucniPojmovi = runtimeService.getVariable(delegateExecution.getId(), "kljucniPojmovi").toString();
	        String naucnaOblast = runtimeService.getVariable(delegateExecution.getId(), "naucnaOblast").toString();
	        String naucniRad = runtimeService.getVariable(delegateExecution.getId(), "naucniRad").toString();
		 	System.out.println("EnterMagazineDataHandler");
	        System.out.println("naslov " + naslov +"koautorIme " + koautorIme +"koatutorEmail " + koatutorEmail +"koautorAdresa " + koautorAdresa +"kljucniPojmovi " + kljucniPojmovi +"naucnaOblast " + naucnaOblast +"naucniRad " + naucniRad );
		
	}

}
