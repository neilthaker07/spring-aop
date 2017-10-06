package edu.sjsu.cmpe275.aop.aspect;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.BlogService;

@Aspect
@Order(2)
public class AuthorizationAspect 
{
	Map<String, HashSet<String>> users = new HashMap<String, HashSet<String>>();
	
	@Before("execution(* edu.sjsu.cmpe275.aop.BlogService.readBlog(..)) && args(userId, blogUserId)")
	public void readCheck(JoinPoint joinPoint, String userId, String blogUserId) throws AccessDeniedException
	{
		if( !userId.equals(blogUserId) && 
				( users.isEmpty() ||
				  users.get(blogUserId) == null ||
				 !users.get(blogUserId).contains(userId)) )
		{
			throw new AccessDeniedException("Blog not for reading");
		}
	}

	@Before("execution(* edu.sjsu.cmpe275.aop.BlogService.shareBlog(..)) && args(userId, blogUserId, targetUserId)")
	public void sharingCheck(JoinPoint joinPoint, String userId, String blogUserId, String targetUserId) throws AccessDeniedException
	{
		if( !userId.equals(blogUserId) && 
			   ( users.isEmpty() || 
				 users.get(blogUserId) == null ||  
				!users.get(blogUserId).contains(userId)) )
		{
			throw new AccessDeniedException("Blog can't be shared");
		}
	}
	
	@After("execution(* edu.sjsu.cmpe275.aop.BlogService.shareBlog(..)) && args(userId, blogUserId, targetUserId)")
	public void sharingToUser(JoinPoint joinPoint, String userId, String blogUserId, String targetUserId)
	{
		if(!targetUserId.equals(blogUserId) && !targetUserId.equals(userId))
		{
			if(!users.containsKey(blogUserId))
			{
				HashSet<String> set = new HashSet<String>();
				set.add(targetUserId);
				users.put(blogUserId, set);
			}
			else
			{
				HashSet<String> existingBlogs = users.get(blogUserId);
				existingBlogs.add(targetUserId);
				users.put(blogUserId, existingBlogs);
			}
		}
	}
	
	@Before("execution(* edu.sjsu.cmpe275.aop.BlogService.unshareBlog(..)) && args(userId, targetUserId)")
	public void unsharingCheck(JoinPoint joinPoint, String userId, String targetUserId) throws AccessDeniedException
	{
		if( !userId.equals(targetUserId) && 
			   ( users.isEmpty() ||
				 users.get(userId) == null	||
				!users.get(userId).contains(targetUserId)) )
		{
			throw new AccessDeniedException("Blog can't be unshared");
		}
	}
	
	@After("execution(* edu.sjsu.cmpe275.aop.BlogService.unshareBlog(..)) && args(userId, targetUserId)")
	public void unsharingToUser(JoinPoint joinPoint, String userId, String targetUserId) throws AccessDeniedException
	{
		if(!targetUserId.equals(userId))
		{
			HashSet<String> existingBlogs = users.get(userId);
			existingBlogs.remove(targetUserId);
			users.put(userId, existingBlogs);
		}
	}

	@Before("execution(* edu.sjsu.cmpe275.aop.BlogService.commentOnBlog(..)) && args(userId, blogUserId, message)")
	public void commentCheck(JoinPoint joinPoint, String userId, String blogUserId, String message) throws AccessDeniedException
	{
		if( !userId.equals(blogUserId) && 
				( users.isEmpty() ||
				  users.get(blogUserId) == null ||
				 !users.get(blogUserId).contains(userId)) )
		{
			throw new AccessDeniedException("Blog can't be commented");
		}
	}
	
	// sample
/*
	@Autowired BlogService blogService;
	
	@After("execution(public void edu.sjsu.cmpe275.aop.BlogService.shareBlog(..))")
	public void dummyAfterAdvice(JoinPoint joinPoint) {
		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		//blogService.shareBlog();
	}
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.BlogService.unshareBlog(..))")
	public void dummyBeforeAdvice(JoinPoint joinPoint) {
		System.out.printf("Before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
	}*/
	
}
