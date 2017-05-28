package model;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.DispatcherServlet;
import org.RequestAttributeEvent;
import org.RequestAttributeListener;

public class ModelAndView {
	   public HttpServletRequest request;
	   Object view;
	   String viewName;
	   
	   // model attribute
	   Map<String,Object> attributes;
	   
	   // 获得与模型关联的context
	   // 在这里即为dispatchServlet
	   DispatcherServlet servlet;
	   
	   // 与这个modelandvew关联的url
	   String url;
	   // 即请求方法
	   
	   // 关联一个url，即请求方法
	   public ModelAndView(DispatcherServlet servlet, String url) {
			this.servlet = servlet;
			this.url = url;
		}
	   
	   public void setAttribute(String name,Object value) {
		      Object instance[]=getServletListeners();
		      RequestAttributeEvent requestAttributeEvent=new RequestAttributeEvent(request,url, name, value);
		      for(int i=0;i<instance.length;i++){
		    	  RequestAttributeListener listener=(RequestAttributeListener) instance[i];
		    	  listener.attributeAdded(requestAttributeEvent);
		    	  
		      }
		   
		   
	   }
	  
	  

	public void removeAttribute(String name) {
		      Object instance[]=getServletListeners();
		      RequestAttributeEvent requestAttributeEvent=new RequestAttributeEvent(request,url, name,null);
		      for(int i=0;i<instance.length;i++){
		    	  RequestAttributeListener listener=(RequestAttributeListener) instance[i];
		    	  listener.attributeRemoved(requestAttributeEvent);
		    	  
		      }
	   }
	   public Object[] getServletListeners() {
		      // 获取servlet  关联的Listeners
		      return servlet.requestAttributeListeners.toArray();  
	   }
	   
	   
	   
	   
	   
	   
	   
	   

}
