package handler;
import java.lang.reflect.Method;

import para.MethodParameter;

public class HandlerMethod {
	   // 
	   Object bean;
	   Method method;
	   // 参数个数
	   MethodParameter methodParameter[];
	public Object getBean() {
		return bean;
	}
	public void setBean(Object bean) {
		this.bean = bean;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public MethodParameter[] getMethodParameter() {
		return methodParameter;
	}
	public void setMethodParameter(MethodParameter[] methodParameter) {
		this.methodParameter = methodParameter;
	}
	   
	   

}
