package fr.mwahCorp.aspects;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect // comme AspectJ (Spring AOP complète AspectJ)
@EnableAspectJAutoProxy
public class LogAspect {
	Logger logger = Logger.getLogger(LogAspect.class.getName());
//	@Around("execution(* fr.mwahCorp.service..*(..))") // Spring Security ne supporte pas call
	@Around("@annotation(fr.mwahCorp.aspects.Log)")
	public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long t1 = System.currentTimeMillis();
		logger.info("From Logging Aspects .. Before" + proceedingJoinPoint.getSignature());
		Object result = proceedingJoinPoint.proceed();
		logger.info("From Logging Aspects .. After" + proceedingJoinPoint.getSignature());
		logger.info("Duration : " + (System.currentTimeMillis()-t1) + " ms");
		return result;
	}
}
