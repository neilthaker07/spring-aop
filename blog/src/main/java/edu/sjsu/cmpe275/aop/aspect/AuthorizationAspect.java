package edu.sjsu.cmpe275.aop.aspect;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AuthorizationAspect 
{
	Map<String, HashSet<String>> abc = new HashMap<String, HashSet<String>>();
	
	@Before("execution(* readBlog(..)) && args(userId, blogUserId)")
	public void reading(JoinPoint joinPoint, String userId, String blogUserId) throws AccessDeniedException
	{
		if(!userId.equals(blogUserId) )
		{
			throw new AccessDeniedException("Blog not for reading");
		}
	}

	@Before("execution(* shareBlog(..)) && args(userId, blogUserId, targetUserId)")
	public void sharingCheck(JoinPoint joinPoint, String userId, String blogUserId, String targetUserId) throws AccessDeniedException
	{
		if(!userId.equals(blogUserId) && !abc.get(blogUserId).contains(userId))
		{
			throw new AccessDeniedException("Blog can't be shared");
		}
	}
	
	@After("execution(* shareBlog(..)) && args(userId, blogUserId, targetUserId)")
	public void sharingToUser(JoinPoint joinPoint, String userId, String blogUserId, String targetUserId)
	{
		if(!targetUserId.equals(blogUserId) && !targetUserId.equals(userId))
		{
			if(!abc.containsKey(blogUserId))
			{
				HashSet<String> set = new HashSet<String>();
				set.add(targetUserId);
				abc.put(blogUserId, set);
			}
			else
			{
				HashSet<String> existingBlogs = abc.get(blogUserId);
				existingBlogs.add(targetUserId);
				abc.put(blogUserId, existingBlogs);
			}
		}
	}
}
