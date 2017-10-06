package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import edu.sjsu.cmpe275.aop.exceptions.NetworkException;

@Aspect
public class RetryAspect {
	
	int count = 0;
	
	@AfterThrowing(pointcut = "execution(* *.*(..))",throwing = "e")
	public void autoRetryNetwork(JoinPoint joinPoint, NetworkException e)
	{
		System.out.println("COUNT : COUNT : "+count);
		if(count == 0)
		{
//			try {
//				throw new NetworkException("After 2 retries also network exception happened.");
//			} catch (NetworkException e1) {
//				e1.printStackTrace();
//			}
		}
		count ++;
		System.out.println("An exception : "+e+ " has been thrown : "+ count);
	}
	
	// sample
/*	@Around("execution(public void edu.sjsu.cmpe275.aop.BlogService.*(..))")
	public void dummyAdvice(ProceedingJoinPoint joinPoint) {
		System.out.printf("Prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
	}
*/
}
