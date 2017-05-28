package proxy;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import help.BeanHelper;
import help.ClassHelper;

public class App {
       public static void main() {
    	   ClassHelper  classHelper=new ClassHelper("work");
    	   System.out.println(classHelper);
    	   // bean instance
    	   BeanHelper.constructor(classHelper.classSet);
//    	   Aspect aspect=null;
    	   // 目标类集合
//    	   Set<Class<?>> targetclses=classHelper.getClassByAnnotation(aspect.value());
    	   
    	   // 获得代理类
    	   
    	   Set<Class<?>> proxyclses=classHelper.getClass(Aspect.class);
    	   Map<Class<?>,Set<Class<?>>> proxyMap=new HashMap<Class<?>, Set<Class<?>>>();
    	   for(Class<?> cls:proxyclses){
    		   Aspect asp=cls.getAnnotation(Aspect.class);
    		   Class<? extends Annotation>  controller=asp.value();
    		   // 目标类集合
    		   Set<Class<?>> targetClses=classHelper.getClass(controller);
    		   // 代理类与目标类集合映射
    		   proxyMap.put(cls, targetClses);
    		   
    	   }
    	   
    	   // 执行
          
    	   // 暂时考虑一个target和一个proxy
    	   
    	   // controllerAscpect 对  user and book 进行代理
    	   // filteraAscpect  对 user and book 进行代理
    	   Map<Class<?>,List<Proxy>> targetMap=new HashMap<Class<?>, List<Proxy>>();
    	   // // 暂时考虑一个target和一个proxy
    	   
    	   
    	   // 更新版 一个target可以有多个proxy
    	   
           try {
			update(targetMap, proxyMap);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
           // 使用代理类
           
           //work.UserControl userControl=(work.UserControl) BeanHelper.getBean(work.UserControl.class);
           //userControl.get();
           
           
           
           
           
           
           
    	   
    	   
    	   
    	   
    	   
    	   
	}
       
   public static void single(Map<Class<?>,List<Proxy>> targetMap,Map<Class<?>,Set<Class<?>>> proxyMap) {
	   for(Map.Entry<Class<?>,Set<Class<?>>> entry:proxyMap.entrySet()){
		   try {
			Proxy proxy=(Proxy) entry.getKey().newInstance();
			List<Proxy> proxies=new ArrayList<Proxy>();
			proxies.add(proxy);
			for(Class<?> target:entry.getValue()){
				
				targetMap.put(target,proxies);
				
				 // 创建代理类
				Object pro=ProxyManager.createProxy(target, proxies);
				
				// 更新beanmap
				
				BeanHelper.set(target, pro);
				
				
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
}    
       
    // 更新版 一个target可以有多个proxy
    public static void update(Map<Class<?>,List<Proxy>> targetMap,Map<Class<?>,Set<Class<?>>> proxyMap) throws InstantiationException, IllegalAccessException {
    	   for(Map.Entry<Class<?>,Set<Class<?>>> entry:proxyMap.entrySet()){
    		   Proxy proxy=(Proxy) entry.getKey().newInstance();
    		   for(Class<?> target:entry.getValue()){
    			   if(targetMap.containsKey(target)){
    				   // 如果目标类已有,继续为目标类添加新的代理
    				   targetMap.get(target).add(proxy);
    			   }else{
    				   List<Proxy> proxies=new ArrayList<Proxy>();
    				   proxies.add(proxy);
    				   targetMap.put(target, proxies);
    				   
    				   // 创建代理类
    					Object pro=ProxyManager.createProxy(target, proxies);
    					
    					// 更新beanmap
    					
    					BeanHelper.set(target, pro);
    				   
    			   }
    			   
    			   
    			   
    			   
    		   }
    		   
    		   
    	   }
    	
		
	}   
       
       
}
