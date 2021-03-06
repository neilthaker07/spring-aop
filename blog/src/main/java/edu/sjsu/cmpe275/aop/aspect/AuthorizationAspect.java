package edu.sjsu.cmpe275.aop.aspect;

import edu.sjsu.cmpe275.aop.exceptions.AccessDeniedExeption;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class AuthorizationAspect 
{
	Map<String, HashSet<String>> users = new HashMap<String, HashSet<String>>();

	@Before("execution(* edu.sjsu.cmpe275.aop.BlogService.readBlog(..)) && args(userId, blogUserId)")
	public void readAuth(JoinPoint joinPoint, String userId, String blogUserId) throws AccessDeniedExeption
	{
		if( !userId.equals(blogUserId) && 
				( users.isEmpty() ||
				  users.get(blogUserId) == null ||
				 !users.get(blogUserId).contains(userId)) )
		{
			throw new AccessDeniedExeption("Blog can't be read");
		}
	}

	@Before("execution(* edu.sjsu.cmpe275.aop.BlogService.shareBlog(..)) && args(userId, blogUserId, targetUserId)")
	public void sharingAuth(JoinPoint joinPoint, String userId, String blogUserId, String targetUserId) throws AccessDeniedExeption 
	{
		if( !userId.equals(blogUserId) && 
			   ( users.isEmpty() || 
				 users.get(blogUserId) == null ||  
				!users.get(blogUserId).contains(userId)) )
		{
			throw new AccessDeniedExeption("Blog can't be shared");
		}
	}
	
	@AfterReturning("execution(* edu.sjsu.cmpe275.aop.BlogService.shareBlog(..)) && args(userId, blogUserId, targetUserId)")
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
	public void unsharingAuth(JoinPoint joinPoint, String userId, String targetUserId) throws AccessDeniedExeption
	{
		if( !userId.equals(targetUserId) && 
			   ( users.isEmpty() ||
				 users.get(userId) == null	||
				!users.get(userId).contains(targetUserId)) )
		{
			throw new AccessDeniedExeption("Blog can't be unshared");
		}
	}
	
	@AfterReturning("execution(* edu.sjsu.cmpe275.aop.BlogService.unshareBlog(..)) && args(userId, targetUserId)")
	public void unsharingToUser(JoinPoint joinPoint, String userId, String targetUserId)
	{
		if(!targetUserId.equals(userId))
		{
			HashSet<String> existingBlogs = users.get(userId);
			existingBlogs.remove(targetUserId);
			users.put(userId, existingBlogs);
		}
	}

	@Before("execution(* edu.sjsu.cmpe275.aop.BlogService.commentOnBlog(..)) && args(userId, blogUserId, message)")
	public void commentAuth(JoinPoint joinPoint, String userId, String blogUserId, String message) throws AccessDeniedExeption
	{
		if( !userId.equals(blogUserId) && 
				( users.isEmpty() ||
				  users.get(blogUserId) == null ||
				 !users.get(blogUserId).contains(userId)) )
		{
			throw new AccessDeniedExeption("Blog can't be commented");
		}
	}
}
