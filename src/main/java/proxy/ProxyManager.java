package proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//创建代理对象
public class ProxyManager {
	   // 根据目标类和一组代理接口的实现类
	   // 创建代理对象
       public static<T> T createProxy(final Class<?> target,final List<Proxy> proxies) {
		      Enhancer enhancer=new Enhancer();
		      Callback callback=new MethodInterceptor() {
				
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					   
					   return new ProxyChain(target.getClass(),obj, method, proxy,args, proxies).doProxyChain();
				}
			};
			Object object=enhancer.create(target, callback);
			return (T) object;
	}
}
