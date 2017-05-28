package para;
import java.lang.reflect.Method;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

public class MethodParameter {
	   ParameterNameDiscoverer discoverer=new LocalVariableTableParameterNameDiscoverer();
       // belong to which method
	   Method method;
	   String parameterName;
	   Class<?> parameterType;
	   int parameterIndex;
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public String getParameterName() {
		// 用asm   
		String  methodArgs[]=discoverer.getParameterNames(method);
		
		parameterName=methodArgs[parameterIndex];
		Class<?> paraType[]=method.getParameterTypes();
		parameterType=paraType[parameterIndex];
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public Class<?> getParameterType() {
		Class<?> paraType[]=method.getParameterTypes();
		parameterType=paraType[parameterIndex];
		return parameterType;
	}
	public void setParameterType(Class<?> parameterType) {
		this.parameterType = parameterType;
	}
	public int getParameterIndex() {
		return parameterIndex;
	}
	public void setParameterIndex(int parameterIndex) {
		this.parameterIndex = parameterIndex;
	}
	   
}
