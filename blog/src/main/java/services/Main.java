package services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exceptions.AccessDeniedExeption;
import exceptions.NetworkException;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx  = new ClassPathXmlApplicationContext("beans.xml");
		BlogServiceImpl blogService = (BlogServiceImpl)ctx.getBean("blogService");
		
		try {
			blogService.readBlog("Alice", "Alice");
			/*blogService.shareBlog("Alice", blogUserId, targetUserId);
			blogService.commentOnBlog(userId, blogUserId, message);
			blogService.unshareBlog(userId, targetUserId);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
