package edu.sjsu.cmpe275.aop.aspect;
import org.aspectj.lang.annotation.Aspect;  // if needed
import org.aspectj.lang.annotation.Around;  // if needed
import org.aspectj.lang.ProceedingJoinPoint; // if needed

@Aspect
public class ValidationAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

	@Around("execution(public void edu.sjsu.cmpe275.aop.BlogService.*(..))")
	public void dummyAdvice(ProceedingJoinPoint joinPoint) {
		System.out.printf("Prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
	}
}