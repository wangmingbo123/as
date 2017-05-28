package org;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.HandlerInterceptor;

public class LogIntercetor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   System.out.println("log request preHandle");
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   System.out.println("log request postHandle");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   System.out.println("log request afterCompletion");
	}
       
}
