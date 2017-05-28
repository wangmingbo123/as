package work;

import java.lang.reflect.Method;
// 切面类

import proxy.Aspect;
import proxy.AspectProxy;
import proxy.Controller;

// 目标类 
// 为注解了Controller的类

@Aspect(Controller.class)
public class ControllerAscpect extends AspectProxy{

	@Override
	public void before(Class<?> cls, Method method, Object[] params) {
		        System.out.println("ControllerAscpect before cls "+cls.getName());
		        System.out.println("ControllerAscpect before method "+method.getName());
	}

	@Override
	public void after(Class<?> cls, Method method, Object[] params) {
		    System.out.println("ControllerAscpect after cls "+cls.getName());
	        System.out.println("ControllerAscpect after method "+method.getName());
		
	}
	   
	 

}
