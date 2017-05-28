package para;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.SimpleTypeConverter;

// 解析普通的参数
public class RequestParamMethodArgumentResolvor implements HandlerMethodArgumentResolver,HandlerMethodReturnValueHandler{

	public void handleReturnValue(Object returnValue, MethodParameter parameter, Container container,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public boolean supportsParameter(MethodParameter parameter) {
           // plain type
		   
		Class<?> paramType=parameter.getParameterType();
		return BeanUtils.isSimpleProperty(paramType);
	}

	public Object resolve(MethodParameter parameter, Container container, HttpServletRequest request) {
		Enumeration<String> names=request.getParameterNames();
		Map<String,String> paraMap=new HashMap<String, String>();
		while(names.hasMoreElements()){
			 String key=names.nextElement();
			 String value=request.getParameter(key);
			 paraMap.put(key, value);
			
		}
		// 参数的名字
	    String name=parameter.getParameterName();
	    
	    // 同时还要进行类型转化
		String value=paraMap.get(name);
		SimpleTypeConverter converter=new SimpleTypeConverter();
		Object arg=converter.convertIfNecessary(value,parameter.getParameterType());
		
		return arg;
	}

}
