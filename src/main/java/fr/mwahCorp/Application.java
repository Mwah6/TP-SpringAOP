package fr.mwahCorp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import fr.mwahCorp.service.IMetier;
import fr.mwahCorp.service.SecurityContext;

// @ComponentScan(basePackages = {"fr.mwahCorp.service", "fr.mwahCorp.aspects"}) // Fait comme dans Spring Boot
@ComponentScan(value = {"fr.mwahCorp"}) // Fait comme dans Spring Boot
public class Application {

	public static void main(String[] args) {
		try {		
			SecurityContext.authenticate("root", "1234", new String[] {"USER"});
			// ApplicationContext applicationContext = new AnnotationConfigApplicationContext("fr.mwahCorp.service", "fr.mwahCorp.apsects"); // On est basé sur les annotations
			ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class); // On est basé sur les annotations
			IMetier metier = applicationContext.getBean(IMetier.class); // On demande à Spring, cherche moi un objet dans le contexte qui implémente cette interface
			System.out.println("********************");
			System.out.println(metier.getClass().getName());
			System.out.println("********************");

			metier.process();
			System.out.println("Result : " + metier.compute());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
