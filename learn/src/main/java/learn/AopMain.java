package learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopMain {

	public static void main(String a[])
	{
		ApplicationContext ctx  = new ClassPathXmlApplicationContext("beans.xml");
		ShapeService s = (ShapeService)ctx.getBean("shapeService");
		System.out.println(s.getCircle().getName());
	}
	
}
