package proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

public class ProxyChain {
	   // 目标类
	   Class<?> targetClass;
	   Object targetObject;
	   Method targetMethod;
	   MethodProxy methodProxy;
	   Object[] methodParas;
	   // 代理类
	   List<Proxy> proxyChain=new ArrayList<Proxy>();
	   
	   public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,
			Object[] methodParas, List<Proxy> proxyChain) {
		super();
		this.targetClass = targetClass;
		this.targetObject = targetObject;
		this.targetMethod = targetMethod;
		this.methodProxy = methodProxy;
		this.methodParas = methodParas;
		this.proxyChain = proxyChain;
	}

	// 
	   int proxyIndex=0;
	   
	   // 执行流程
	   // 先执行所有的代理类，最后执行所有的目标类
	   
	   public Object doProxyChain() throws Throwable {
		      Object result=null;
		      if(proxyIndex<proxyChain.size()){
		    	  // 调用代理方法
		    	  result=proxyChain.get(proxyIndex++).doProxy(this);
		      }else{
		    	  // 调用目标方法
		    	  result=methodProxy.invokeSuper(targetObject, methodParas);
		      }
		      
		      return result;
	}
	   
	   
	   
	   
	
	    
	

}
