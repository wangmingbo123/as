package para;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;

public class HandlerMethod {
	   public void setBean( Object bean) {
		      this.bean=bean; 
	}
	   Class<?> beanType;
	   // 
	   Object bean;
	   Method method;
	   // 参数个数
	   MethodParameter methodParameter[];
	public Object getBean() {
		return bean;
	}
	public void setBeanCls(Class clsBean) {
		
		try {
			this.bean =clsBean.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public MethodParameter[] getMethodParameter() {
		// 根据method构造参数个数
		// 参数数目
		int parameterCount=method.getParameterTypes().length;
		this.methodParameter=new MethodParameter[parameterCount];
		for(int i=0;i<parameterCount;i++){
			methodParameter[i]=new HandlerMethodParameter(method,i);
		}
		
		return methodParameter;
	}
	public void setMethodParameter(MethodParameter[] methodParameter) {
		this.methodParameter = methodParameter;
	}
	
	// 判断该方法是否有有某某的注解
	// 如@modelAttribute
	public <A extends Annotation> A getMethodAnnotation(Class<A> annotationType) {
		   return AnnotationUtils.findAnnotation(method, annotationType);
		
	}
	
	// 获得controller type
	public Class<?> getBeanType() {
		   return bean.getClass();
	}
	
	
	
	
	   

}
