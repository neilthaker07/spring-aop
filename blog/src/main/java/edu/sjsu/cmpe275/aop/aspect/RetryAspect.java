package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.exceptions.NetworkException;

@Order(3)
@Aspect
public class RetryAspect {
	
	@Around("allBlogServices()")
	public void autoRetryNetwork(ProceedingJoinPoint proceedingJoinPoint) throws NetworkException
	{
		try {
			proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			try {
				//System.out.println("Try to fix network exception: Try 1");
				proceedingJoinPoint.proceed();
			} catch (Throwable e1) {
				try {
					//System.out.println("Try to fix network exception: Try 2");
					proceedingJoinPoint.proceed();
				} catch (Throwable e2) {
					throw new NetworkException("Network issue, tried 2 times but failed.");
				}
				//System.out.println("Network issue fixed after 2 tries.");
			}
			//System.out.println("Network issue fixed after 1 try.");
		}
	}
	
	@Pointcut("within(edu.sjsu.cmpe275.aop.BlogServiceImpl)")
	public void allBlogServices(){}
}
