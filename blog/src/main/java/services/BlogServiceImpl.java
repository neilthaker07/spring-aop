package services;

import exceptions.AccessDeniedExeption;
import exceptions.NetworkException;

public class BlogServiceImpl implements BlogService{

	public Blog readBlog(String userId, String blogUserId)
			throws AccessDeniedExeption, NetworkException {
		// TODO Auto-generated method stub
		return null;
	}

	public void shareBlog(String userId, String blogUserId,
			String targetUserId) throws AccessDeniedExeption, NetworkException {
		// TODO Auto-generated method stub
		
	}

	public void unshareBlog(String userId, String targetUserId)
			throws AccessDeniedExeption, NetworkException {
		// TODO Auto-generated method stub
		
	}

	public void commentOnBlog(String userId, String blogUserId, String message)
			throws AccessDeniedExeption, NetworkException,
			IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
