package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;

import edu.sjsu.cmpe275.aop.exceptions.NetworkException;

public class RetryAspect {
	
	int count = 0;
	
	@AfterThrowing(pointcut = "execution(* *.*(..))",throwing = "e")
	public void autoRetryNetwork(JoinPoint joinPoint, NetworkException e)
	{
		System.out.println("COUNT : COUNT : "+count);
		if(count == 0)
		{
			/*try {
				throw new NetworkException("After 2 retries also network exception happened.");
			} catch (NetworkException e1) {
				e1.printStackTrace();
			}*/
		}
		count ++;
		System.out.println("An exception : "+e+ " has been thrown : "+ count);
	}
	

}
