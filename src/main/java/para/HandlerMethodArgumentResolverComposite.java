package para;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HandlerMethodArgumentResolverComposite implements HandlerMethodArgumentResolver{
	   
	  List<HandlerMethodArgumentResolver> argumentResolvers=new LinkedList<HandlerMethodArgumentResolver>();
	  public HandlerMethodArgumentResolverComposite() {
		    argumentResolvers.add(new ModelAttributeMethodProcessor());
		    argumentResolvers.add(new RequestParamMethodArgumentResolvor());
	}

	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object resolve(MethodParameter parameter, Container container, HttpServletRequest request) {
		HandlerMethodArgumentResolver  resolver=getHandlerMethodArgumentResolver(parameter);
              		
		return resolver.resolve(parameter, container, request);
	}
	HandlerMethodArgumentResolver getHandlerMethodArgumentResolver(MethodParameter parameter){
		for(HandlerMethodArgumentResolver resolver:argumentResolvers){
			if(resolver.supportsParameter(parameter)){
				System.out.println("use resolver "+resolver.toString()+"  resolver  "+parameter.toString());
			    return resolver; 
			}
			
			
		}
		return null;
	}
	public void addResolver() {
		    argumentResolvers.add(new ModelAttributeMethodProcessor());
		    argumentResolvers.add(new RequestParamMethodArgumentResolvor());
	}
	
	
	
       
}
