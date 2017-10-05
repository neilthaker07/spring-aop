package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ValidationAspect {
	
	@Before("args(userId, blogUserId)")
	public void userIds(JoinPoint joinPoint, String userId, String blogUserId)
	{
		checkUserLength(userId);
		checkUserLength(blogUserId);
	}
	
	@Before("execution(* share*(..)) && args(userId, blogUserId, targetUserId)")
	public void shareUserId(JoinPoint joinPoint, String userId, String blogUserId, String targetUserId)
	{
		checkUserLength(userId);
		checkUserLength(blogUserId);
		checkUserLength(targetUserId);
	}
	
	@Before("execution(* comment*(..)) && args(userId, blogUserId, comment)")
	public void commentLength(JoinPoint joinPoint, String userId, String blogUserId, String comment)
	{
		checkUserLength(userId);
		checkUserLength(blogUserId);
		checkCommentLength(comment);
	}
	
	public void checkUserLength(String user)
	{
		if(user.length() < 3 || user.length() > 16)
		{
			throw new IllegalArgumentException();
		}
	}
	
	public void checkCommentLength(String comment)
	{
		if(comment==null || comment.length() < 1 || comment.length() > 100)
		{
			throw new IllegalArgumentException();
		}
	}
	
	
}