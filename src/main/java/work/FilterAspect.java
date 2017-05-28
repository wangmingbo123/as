package work;

import java.lang.reflect.Method;

import proxy.Aspect;
import proxy.AspectProxy;
import proxy.Controller;

@Aspect(Controller.class)
public class FilterAspect extends AspectProxy{
	@Override
	public void before(Class<?> cls, Method method, Object[] params) {
		 System.out.println("FilterAspect before cls "+cls.getName());
	        System.out.println("FilterAspect  before method "+method.getName());
	}

	@Override
	public void after(Class<?> cls, Method method, Object[] params) {
		  System.out.println("FilterAspect  after cls "+cls.getName());
	        System.out.println("FilterAspect  after method "+method.getName());
	}
}
