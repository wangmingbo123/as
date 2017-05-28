package model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import annotation.ModelAttribute;
import para.Container;
import para.InvocableHandlerMethod;

//
public class ModelFactory {
	
	   
	    
	    public ModelFactory(List<InvocableHandlerMethod> attributeMethods) {
		
		this.attributeMethods = attributeMethods;
	}
		// 添加有@modelAttribute的方法
        public List<InvocableHandlerMethod> attributeMethods;
        
        // 将modelAttribute里面的值添加到container里面
        public void initModel(HttpServletRequest request,Container container) {
			   invokeModelAttributeMethods(request, container);
			   // 还要处理返回值
		}
        // 
        public void invokeModelAttributeMethods(HttpServletRequest request,Container container) {
        	   for(InvocableHandlerMethod attrMethod:attributeMethods){
        		   // 模型的名字
        		   String modelName=attrMethod.getMethodAnnotation(ModelAttribute.class).value();
        		   Object returnValue=attrMethod.invokeForRequest(request, container);
        		   System.out.println("model name "+modelName);
        		   System.out.println("return value "+returnValue);
        		   
        		   container.addAttribute(modelName, returnValue);
        	   }
        	   // print model info
        	   
        	   
        	   
        	   
        	  
			   
        	   
		}
        
        
        
}
