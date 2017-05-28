package para;

import java.lang.reflect.Method;

public class HandlerMethodParameter extends MethodParameter{
	// 参数位置   
	public HandlerMethodParameter(Method method,int index) {
		  //
		  this.method=method;
		  this.parameterIndex=index;
		
	}

}
