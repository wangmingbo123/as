package proxy;

import java.lang.reflect.Method;
// 切面类

// 目标类 
// 为注解了Controller的类

@Aspect(Controller.class)
public class ControllerAscpect extends AspectProxy{

	@Override
	public void before(Class<?> cls, Method method, Object[] params) {
		   
	}

	@Override
	public void after(Class<?> cls, Method method, Object[] params) {
		
	}
	   
	 

}
