package handler;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerExecutionChain {
	   List<HandlerInterceptor> interceptors=new ArrayList<HandlerInterceptor>();
	   
	  public void addInterceptor(HandlerInterceptor handlerInterceptor) {
		     interceptors.add(handlerInterceptor);
	  }
	  
	  public void remove(HandlerInterceptor handlerInterceptor) {
		      interceptors.remove(handlerInterceptor);
	  }
	
	   public void applypreHandler(HttpServletRequest request, HttpServletResponse response) {
		      for(int i=0;i<interceptors.size();i++){
		    	  HandlerInterceptor handlerInterceptor=interceptors.get(i);
		    	  try {
					handlerInterceptor.preHandle(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		   
		
	   }
	   public void applypostHandle(HttpServletRequest request, HttpServletResponse response) {
		   for(int i=0;i<interceptors.size();i++){
		    	  HandlerInterceptor handlerInterceptor=interceptors.get(i);
		    	  try {
					handlerInterceptor.postHandle(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		
	   }
	   public void applyafterCompletion(HttpServletRequest request, HttpServletResponse response) {
		   for(int i=0;i<interceptors.size();i++){
		    	  HandlerInterceptor handlerInterceptor=interceptors.get(i);
		    	  try {
					handlerInterceptor.afterCompletion(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		
	  }
	  public int  size() {
		     return interceptors.size();
	} 
	   
	   
	  

}
