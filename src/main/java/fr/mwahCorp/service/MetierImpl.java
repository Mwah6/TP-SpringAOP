package fr.mwahCorp.service;

import org.springframework.stereotype.Service;

import fr.mwahCorp.aspects.Log;
import fr.mwahCorp.aspects.SecuredByAspect;

@Service
public class MetierImpl implements IMetier{

	@Log
	@SecuredByAspect(roles= {"USER","ADMIN"})
	public void process() {
	System.out.println("Business Process...");
		
	}
	@SecuredByAspect(roles= {"ADMIN"})
	public double compute() {
		double data = 78;
		System.out.println("Business Computing and returning");
		return data;
	}

}
