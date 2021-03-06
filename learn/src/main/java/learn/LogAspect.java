package learn;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {
	
	@Before("allGetters()")
	public void loggingAdvice(Joinpoint joinpoint)
	{
		System.out.println("logging advice");
		//System.out.println("Methods : joinpoint "+joinpoint.toString());
	}
	
	@Pointcut("execution(* get*())")
	public void allGetters()
	{}
	
	
	
}
