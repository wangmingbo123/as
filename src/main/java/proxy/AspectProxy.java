package proxy;

import java.lang.reflect.Method;

public abstract class AspectProxy implements Proxy {
       // 抽象类实现接口
	   // 不必须要实现接口的方法
	   public Object doProxy(ProxyChain proxyChain) throws Throwable {
		      Object object=null;
		      begin();
		      Class<?> cls=proxyChain.targetClass;
		      Method method=proxyChain.targetMethod;
		      Object params[]=proxyChain.methodParas;
		      if(intercept(cls, method, params)){
		    	  // 进行拦截
		    	  before(cls, method, params);
		    	  
//		    	  object=method.invoke(cls,params);
		    	  object=proxyChain.doProxyChain();
		    	  
		    	  after(cls, method, params);
		      }else{
		    	  object=proxyChain.doProxyChain();
		      }
		      
		      
		      end();
		      
		      
		      return object;
		      
		
	   }
	   
	   // 
	   public void begin() {
		
	}
	   
	   public void end() {
		
	}
	   
	   //  是否拦截
	   public boolean intercept(Class<?> cls,Method method,Object params[]) {
		return true;
	}
	   
	   
	   public void before(Class<?> cls,Method method,Object params[]) {
		
	   }
	   public void after(Class<?> cls,Method method,Object params[]) {
			
	   }
	   
	   
	   
	   
	   
	   
	   
}
