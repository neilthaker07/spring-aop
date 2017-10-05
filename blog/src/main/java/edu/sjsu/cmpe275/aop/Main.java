package edu.sjsu.cmpe275.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.cmpe275.aop.exceptions.AccessDeniedExeption;
import edu.sjsu.cmpe275.aop.exceptions.NetworkException;

public class Main {

	public static void main(String[] args) 
	{
		ApplicationContext ctx  = new ClassPathXmlApplicationContext("beans.xml");
		BlogService blogService = (BlogService) ctx.getBean("blogService");
		
		try {
			
			blogService.readBlog("Alice", "Alice");
			blogService.shareBlog("Alice", "Alice", "Bob");
			blogService.readBlog("Bob", "Alice");

			//blogService.unshareBlog("Alice", "Alice");
			//blogService.commentOnBlog("22222", "11111", "789");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}