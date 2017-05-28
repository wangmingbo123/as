package para;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.web.bind.ServletRequestDataBinder;

import converter.HttpMessageConverter;
import converter.MappingJackson2HttpMessageConverter;
import converter.StringHttpMessageConverter;
import proxy.UserControl;
import work.User;

// 添加返回值的支持
public class ModelAttributeMethodProcessor implements HandlerMethodArgumentResolver,HandlerMethodReturnValueHandler{
       // 借助HttpMessageConverter来完成转化
//	   HttpMessageConverter<String> converter=new StringHttpMessageConverter();
	   List<HttpMessageConverter<?>> converters=new ArrayList<HttpMessageConverter<?>>();
	   
	   public ModelAttributeMethodProcessor() {
              converters.add(new StringHttpMessageConverter());
              converters.add(new MappingJackson2HttpMessageConverter());
	   }  
	
	
	public Object resolve(MethodParameter parameter, Container container, HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 参数的名字
		String name=parameter.getParameterName();
		/*Object attribute=new Object();
		if(parameter.parameterType.equals(String.class)){
			attribute=(String)attribute;
		}else if(parameter.parameterType.equals(Integer.class)){
			attribute=(Integer)attribute;
		}*/
		/*Object attribute=new Object();
		// Integer 忽略
		if(!parameter.getParameterType().equals(Integer.class)){
		   attribute=createAttribute(parameter);
		}else{
			attribute=(Integer)attribute;
		}*/
		
		// 进行绑定
		
		//然后将结果放到container里面
		
		
		// 参数绑定,暂时做简化处理
		
		//   参数来源
		//   1 request里面的参数
		//Object attribute=binder(name,parameter.parameterType , request);
//		System.out.println("attribute cls type "+attribute.getClass());
		//ServletRequestDataBinder dataBinder=new ServletRequestDataBinder(target, objectName);
		String value=createAttribue(name, request);
		Object arg=null;
		if(value!=null){
			// 说明它是简单变量
			// 将value(string)转化为parameter.parameterType类型
			SimpleTypeConverter converter=new SimpleTypeConverter();
			// 
		    arg=converter.convertIfNecessary(value,parameter.parameterType);
		    System.out.println("arg type "+arg);
		   
			
		}
		// 应该是javabean类型
		arg=BeanUtils.instantiateClass(parameter.parameterType);
		ServletRequestDataBinder binder=new ServletRequestDataBinder(arg,name);
		binder.bind(request);
		// 返回绑定结果
		return binder.getTarget();
	}
	//根据属性名到request里面找到他的值
	public String  createAttribue(String attrName,HttpServletRequest request) {
		Enumeration<String> names=request.getParameterNames();
		Map<String,String> paraMap=new HashMap<String, String>();
		while(names.hasMoreElements()){
			 String key=names.nextElement();
			 String value=request.getParameter(key);
			 paraMap.put(key, value);
			
		}
		if(paraMap.containsKey(attrName))
		  return paraMap.get(attrName);
		return null;
		
	}
	
	
	
	public Object binder(String name,Class clsType,HttpServletRequest request) {
		Enumeration<String> names=request.getParameterNames();
		Map<String,String> paraMap=new HashMap<String, String>();
		while(names.hasMoreElements()){
			 String key=names.nextElement();
			 String value=request.getParameter(key);
			 paraMap.put(key, value);
			
		}
        // 判断attribute里面有没有字段在paraMap里面
		
		// 即将paraMap里面的值填充到attribue里面
		
		//  对attribute进行判别
		    //1  普通类型  Integer or String 等
		    //2 java bean  类型
		// 目前支持这两种
		/*if(clsType.equals(String.class)){
			String value=paraMap.get(name);
			// 到paraMap里面获取参数值
			
			return value;
			
			
		}else if(clsType.equals(Integer.class)){
			// java bean类型
			
			return Integer.parseInt(paraMap.get(name));
			
			
			
		}*/
		// 先从paraMap里面取
		String value=paraMap.get(name);
		if(value==null){
			// 请求参数里面没有这个参数值
			return null;
		}else{
			// 不为null,则看clsType
		    SimpleTypeConverter converter=new SimpleTypeConverter();
		    Object arg=converter.convertIfNecessary(value,clsType);
		    System.out.println("arg type "+arg);
		    return arg;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public Object createAttribute(MethodParameter parameter) {
		   // 使用spring bean 的api
		   return BeanUtils.instantiate(parameter.getParameterType());
	}
	

	public boolean supportsParameter(MethodParameter parameter) {
		
		// 没有加@ModelAttribute
		// 判断是否为简单的变量类型
		Class<?> paramType=parameter.getParameterType();
		return !BeanUtils.isSimpleProperty(paramType);
	}
	@Test
	public void name() {
		User user=new User();
		BeanWrapperImpl beanWrapperImpl=new BeanWrapperImpl(user);
		Class<?> cls=beanWrapperImpl.getPropertyType("userName");
		System.out.println(user.userName);
		beanWrapperImpl.setPropertyValue("userName","yu");
		System.out.println(user.userName);
		System.out.println(cls);
		
	}

	public void handleReturnValue(Object returnValue, MethodParameter parameter, Container container,
			HttpServletResponse response) {
		    // 消除泛型
		    writeMessageWithConverter(returnValue, parameter, response);
		  
		
	}
	public <T> void writeMessageWithConverter(T returnValue,MethodParameter parameter,HttpServletResponse response) {
	                if(returnValue==null)
	                	return;
		
		            //converter.write((String) returnValue, response);
		
		            // 遍历converters找到相应的转化器
		            Class clazz=returnValue.getClass();
		            
		            for(HttpMessageConverter<?> converter:converters){
		            	// plain
		            	if(BeanUtils.isSimpleProperty(clazz) && converter instanceof StringHttpMessageConverter){
		            		// 类型转换
		            		((HttpMessageConverter<T>)converter).write(returnValue, response);
		            		System.out.println("write return value "+returnValue+" using "+converter);
		            		return;
		            	}else if(!BeanUtils.isSimpleProperty(clazz) && converter instanceof MappingJackson2HttpMessageConverter){
		            	   //java bean
		            		((HttpMessageConverter<T>)converter).write(returnValue, response);
		            		System.out.println("write return value "+returnValue+" using "+converter);
		            		return;
		            	}
		            }
		
	}
	
	

}
