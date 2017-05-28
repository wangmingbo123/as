package help;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import annotation.Action;
import handler.Handler;
import para.HandlerMethod;
import para.InvocableHandlerMethod;

public class ControllerHelp {
	   Logger logger=Logger.getLogger(ControllerHelp.class);
	   public static ClassHelper classHelper=null;
	   // request-> handler
	   static Map<String,Handler> requestAndHandler=new HashMap<String, Handler>();
	   public static Map<String,InvocableHandlerMethod> requestAndHandlerMethod=new HashMap<String,InvocableHandlerMethod>();
       static{
    	   String basePackage=ConfigHelp.getString("basePackage");
    	   System.out.println("basePackage value "+basePackage);
    	   classHelper=new ClassHelper(basePackage);
   		   Set<Class<?>> controlerBean=classHelper.getClassController();
   		   for(Class<?> cls:controlerBean){
   			   Method[] methods=cls.getDeclaredMethods();
   			   // 判断是否有action注解
   			   for(Method  method:methods){
                   if(method.isAnnotationPresent(Action.class)){
                	  Action action=method.getAnnotation(Action.class);
                	  String request=action.url();
                	  Handler handler=new Handler(cls, method);
                      requestAndHandler.put(request, handler);
                      
                      // 解析请求方法
//                      HandlerMethod handlerMethod=new HandlerMethod();
                      InvocableHandlerMethod handlerMethod=new InvocableHandlerMethod(cls,method);
                      handlerMethod.setBean(cls);
                      handlerMethod.setMethod(method);
                      requestAndHandlerMethod.put(request, handlerMethod);
                      
                      

                   }   				   
   			   }
   		   }
    	   
       }
       // 根据request获得Handler
       public Handler getHandler(String request) {
		      return requestAndHandler.get(request);   
	}
     
       // update
       
       public InvocableHandlerMethod getHandlerUpdate(String request) {
		      return requestAndHandlerMethod.get(request);   
	   }
       
       
    @Override
    public String toString() {
    	for(Map.Entry<String,Handler> entry:requestAndHandler.entrySet()){
    		String url=entry.getKey();
    		Handler handler=entry.getValue();
    		logger.info("url "+url+"  map "+handler.toString());
    		
    		
    		
    	}
    	
    	
    	return super.toString();
    }
       
       
       
       
}
