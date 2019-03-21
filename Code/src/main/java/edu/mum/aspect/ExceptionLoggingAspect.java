package edu.mum.aspect;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect {

	private Logger log = Logger.getLogger(getClass());

	@Pointcut("execution(* edu.mum.service..*(..))")
	public void serviceLayerMethods() {

	}

	@AfterThrowing(pointcut = "serviceLayerMethods()", throwing = "ex")
	public void logError(JoinPoint joinPoint, Exception ex) {
		
		StringWriter stack = new StringWriter();
		ex.printStackTrace(new PrintWriter(stack));

		Object[] signatureArgs = joinPoint.getArgs();
		for (Object signatureArg : signatureArgs) {
			stack.append("Arg: " + signatureArg);
		}

		log.error("----------------------------------------------------------");
		log.error(stack.toString());
		log.error("----------------------------------------------------------");
	}
}