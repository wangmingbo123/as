package handler;
import java.lang.reflect.Method;

public class Handler {
	   // 控制器+方法
	   public Class<?> controllerClass;
	   public Method method;
	public Handler(Class<?> controllerClass, Method method) {
		super();
		this.controllerClass = controllerClass;
		this.method = method;
	}
	public Class<?> getControllerClass() {
		return controllerClass;
	}
	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	
	
	
	

}
