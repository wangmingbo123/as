package para;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.ModelAttribute;
import binder.WebDataBindler;
import model.ModelFactory;

public class InvocableHandlerMethod extends HandlerMethod{
	
//	   List<HandlerMethodReturnValueHandler> returnValueHandlers=new
	   HandlerMethodReturnValueHandler returnValueHandler=new ModelAttributeMethodProcessor();
	   
	   // 多个参数解析器
	   HandlerMethodArgumentResolverComposite resolverComposite=new HandlerMethodArgumentResolverComposite();
	   
	    
	   
	   public InvocableHandlerMethod(Class<?> beanType,Method method) {
		       this.beanType=beanType;
		       this.method=method;
	}

	HandlerMethodArgumentResolver resolver=new ModelAttributeMethodProcessor();
	
	   public Object invokeForRequest(HttpServletRequest request,Container container) {
		      // ModelFactory
		      // 根据 handlerMethod 获得modelFactory
		   
		      //获得参数
		      Object args[]=getMethodArgumentValues(request, container);
		   
		 
		      
		      // 执行target method
		      Object returnValue=invoke(args);
		      
		      return returnValue;
		   
		
	}
	   
	public Object[] getMethodArgumentValues(HttpServletRequest request,Container container) {
		   
		   // super class method
		   MethodParameter methodParameter[]=getMethodParameter();
		   Object arg[]=new Object[methodParameter.length];
		   
		   // invoke HandlerMethodArgumentResolver  to parse the method argument
		   for(int i=0;i<methodParameter.length;i++){
			   MethodParameter parameter=methodParameter[i];
			   
			   arg[i]=resolverComposite.resolve(parameter, container, request);
			   System.out.println("# begin解析的参数 ");
			   System.out.println("参数名  "+parameter.parameterName);
			   System.out.println("参数值  "+arg[i]);
			   if(arg[i]!=null)
			   System.out.println("参数类型  "+arg[i].getClass());
			   else
			   System.out.println("参数类型  null");	   
			   System.out.println("# end解析的参数 ");
			   
		   }
		   return arg;
		
	}
	public Object invoke(Object... args) {
		   try {
			   getMethod().setAccessible(true);
			return getMethod().invoke(getBean(), args);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public Object invokeForquest(WebDataBindler webDataBindler) {
		// TODO Auto-generated method stub
		return null;
	}

	//获得modelFactory
		public ModelFactory getModelFactory(InvocableHandlerMethod handlerMethod) {
			   Map<Class<?>,Set<Method>> modelAttribute=new HashMap<Class<?>, Set<Method>>();
			   List<InvocableHandlerMethod> attrsMethod=new ArrayList<InvocableHandlerMethod>();
				  
			   Set<Method> methods=new HashSet<Method>();
			   Class<?> beanType=handlerMethod.getBeanType();
			   for(Method method:beanType.getMethods()){
				   if(method.isAnnotationPresent(ModelAttribute.class)){
//					   modelAttribute.put(beanType,method);
					   methods.add(method);
					   attrsMethod.add(create(beanType, method));
				   }
			   }
			   modelAttribute.put(beanType, methods);
			 
			   // build  工厂
			   
			   return new ModelFactory(attrsMethod);
			   
			   
			
		}
		public InvocableHandlerMethod create(Class clsBean,Method method) {
			   return new InvocableHandlerMethod(clsBean,method);
		}
		
		// 目前写到这里
		public void invokeAndHandle(HttpServletRequest request,HttpServletResponse response,Container container) {
			   Object returnValue=invokeForRequest(request, container);
			   returnValueHandler.handleReturnValue(returnValue,null, container, response);
			   
		}
		
		
		
	
}
