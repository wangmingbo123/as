package proxy;

import java.lang.reflect.Method;

//@Aspect(Controller.class)
public class FilterAspect extends AspectProxy{
	@Override
	public void before(Class<?> cls, Method method, Object[] params) {
		   
	}

	@Override
	public void after(Class<?> cls, Method method, Object[] params) {
		
	}
}
