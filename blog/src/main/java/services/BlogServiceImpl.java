package services;

import exceptions.AccessDeniedExeption;
import exceptions.NetworkException;

public class BlogServiceImpl implements BlogService{

	public Blog readBlog(String userId, String blogUserId)
			throws AccessDeniedExeption, NetworkException 
	{
		
		return new Blog();
	}

	public void commentOnBlog(String userId, String blogUserId, String message)
			throws AccessDeniedExeption, NetworkException,
			IllegalArgumentException 
	{
		
	}
	
	public void shareBlog(String userId, String blogUserId,
			String targetUserId) throws AccessDeniedExeption, NetworkException 
	{
		
	}

	public void unshareBlog(String userId, String targetUserId)
			throws AccessDeniedExeption, NetworkException 
	{
		//throw new NetworkException("111111");
		//throw new NetworkException("22222");
	}

}
