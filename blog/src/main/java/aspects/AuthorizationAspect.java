package aspects;

import java.nio.file.AccessDeniedException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AuthorizationAspect 
{
	@Before("execution(* readBlog(..)) && args(userId, blogUserId)")
	public void reading(JoinPoint joinPoint, String userId, String blogUserId)
	{
		// userId - reader
		// blogUserId - holder
		if(!userId.equals(blogUserId) && )
		{
			throw new AccessDeniedException("Blog not for reading");
		}
	}
	
}
