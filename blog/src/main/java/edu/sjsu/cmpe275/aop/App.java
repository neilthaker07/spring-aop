package edu.sjsu.cmpe275.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) 
	{
        /***
         * Following is a dummy implementation of App to demonstrate bean creation with Application context.
         * You may make changes to suit your need, but this file is NOT part of the submission.
         */

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        BlogService blogService = (BlogService) ctx.getBean("blogService");

        try {
        	blogService.shareBlog("Alice", "Alice", "Bob");
        	blogService.readBlog("Bob", "Alice");
        	blogService.shareBlog("Alice", "Alice", "Charles");
        	blogService.shareBlog("Bob", "Alice", "Don");
        	blogService.readBlog("Don", "Alice");
        	blogService.readBlog("Charles", "Alice");
        	//blogService.readBlog("Charles", "Bob");
            blogService.commentOnBlog("Bob", "Alice", "Nice work!");
            blogService.commentOnBlog("Don", "Alice", "Great");
            blogService.commentOnBlog("Charles", "Alice", "Great");
            //blogService.commentOnBlog("Charles", "Bob", "Great");
            blogService.unshareBlog("Alice", "Bob");
            blogService.shareBlog("Charles", "Alice", "Bob");
            blogService.readBlog("Bob", "Alice");
            //blogService.unshareBlog("Alice", "Bob");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ctx.close();
    }
}
