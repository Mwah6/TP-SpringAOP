package fr.mwahCorp.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import fr.mwahCorp.service.SecurityContext;

@Aspect
@Component
@EnableAspectJAutoProxy
public class AuthorizationAspect {
	@Around(value = "@annotation(securedByAspect)", argNames = "pjp, securedByAspect")
	public Object secure(ProceedingJoinPoint pjp, SecuredByAspect securedByAspect) throws Throwable {
		String[] roles = securedByAspect.roles();
		boolean authorized = false;
		for (String r: roles) {
			if (SecurityContext.hasRole(r)) {
				authorized = true;
				break;
			}
		}
		if (authorized) {
			Object result = pjp.proceed();
			return result;
		}
		throw new RuntimeException("Unauthorized => 403");
	}
}
