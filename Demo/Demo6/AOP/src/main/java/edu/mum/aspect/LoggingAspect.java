package edu.mum.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	// Isolates/restricts WHERE @Logging can be applied
	 @Pointcut("execution(* edu.mum.service.OrderService.save(..))")
//	 @Pointcut("execution(* edu.mum.service..update(..))")    // - If you want to AOP ONLY updates...IGNORES Save
	    public void applicationMethod() {}

	  /* @Pointcut("@annotation(edu.mum.aspect.annotation.Logging)")
	    public void logging() {}*/
	
	// Remove logging() &&  AND it will do a "regular" execution Point cut.
		  @Before("applicationMethod()")
	  public void logResource(JoinPoint joinPoint) {
		  Logger log = Logger.getLogger("");
		    log.info("   ********* TARGET CLASS : " + joinPoint.getSignature().getName() + "    **********");
		    System.out.println();
				    System.out.println( "   **********     TARGET CLASS : " + 
	    			joinPoint.getSignature().getDeclaringTypeName() + "." +
	    			joinPoint.getSignature().getName() + 
	    				    			"    **********");
	  }

}
