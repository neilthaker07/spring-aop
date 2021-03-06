package edu.sjsu.cmpe275.aop;

import edu.sjsu.cmpe275.aop.exceptions.AccessDeniedExeption;
import edu.sjsu.cmpe275.aop.exceptions.NetworkException;

public class BlogServiceImpl implements BlogService{
	 
	/***
     * Following is a dummy implementation.
     * You can tweak the implementation to suit your need, but this file is NOT part of the submission.
     */
	int count = 0;
	int count2 = 0;
	int count3 = 0;
	int count4 = 0;
	public Blog readBlog(String userId, String blogUserId) throws AccessDeniedExeption, NetworkException 
	{
		if(count < 2)
		{
			count++;
			throw new NetworkException("throwing manually ");
		}
		System.out.printf("User %s requests to read user %s's blog\n", userId, blogUserId);
		return null;
	}

	public void shareBlog(String userId, String blogUserId, String targetUserId)
			throws AccessDeniedExeption, NetworkException 
	{
		System.out.printf("User %s shares user %s's blog with user %s\n", userId, blogUserId, targetUserId);
		if(count2 < 2)
		{
			count2++;
			throw new NetworkException("throwing manually ");
		}
	}

	public void unshareBlog(String userId, String targetUserId) throws AccessDeniedExeption, NetworkException 
	{
		if(count3 < 2)
		{
			count3++;
			throw new NetworkException("throwing manually ");
		}
		System.out.printf("User %s unshares his/her own blog with user %s\n", userId, targetUserId);
	}

	public void commentOnBlog(String userId, String blogUserId, String message)
			throws AccessDeniedExeption, IllegalArgumentException, NetworkException 
	{
		if(count3 < 2)
		{
			count3++;
			throw new NetworkException("throwing manually ");
		}
		System.out.printf("User %s commented on %s's blog\n", userId, blogUserId);		
	}

}
