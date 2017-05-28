package binder;
import java.util.List;

import para.InvocableHandlerMethod;

public class InitBinderDataBinderFactory implements WebDataBinderFactory{
       // 添加有@InitBinder方法
	List<InvocableHandlerMethod> initMethods;
	
	public WebDataBindler createDataBinder(String requestUrl, Object target, String objectName) {
		
		return null;
	}
	
	// 进行@InitBinder
	public void initBinder(WebDataBindler webDataBindler,String requestUrl) {
		   for(InvocableHandlerMethod initMethod:initMethods){
			   if(isInitBinderApplicable(initMethod)){
				  // 反射调用该方法
				   Object returnValue=initMethod.invokeForquest(webDataBindler);
				   if(returnValue!=null){
					   // 手动抛异常
					   throw new IllegalStateException("@InitBinder method should return void "+initMethod);
				   }
				   
			   }
		   }   
		
		
	}
	
	
	// 判断该方法是否有@initBinder注解
	public boolean isInitBinderApplicable(InvocableHandlerMethod initMethod) {
		   boolean flag=false;
		   
		   return flag;
		   
		
	}
	
	

}
