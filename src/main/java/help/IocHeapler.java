package help;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import annotation.Inject;


// ioc
public class IocHeapler {
	public static Set<Class<?>> clsSet=new HashSet<Class<?>>();
       // beanMap
	public static Set<Class<?>> controlerBean=new HashSet<Class<?>>();
	public static Map<Class<?>,Object> beanMap=new HashMap<Class<?>, Object>();
	
	 public Object getBean(Class<?> beanName) {
		    return beanMap.get(beanName);
	}
	public void beginIoc() {
		 /*String basePackage=ConfigHelp.getString("basePackage"); 
			ClassHelper classHelper=new ClassHelper(basePackage);
			//controlerBean=classHelper.getClassController();
			// 包含controller and inject 注解
		    controlerBean=classHelper.getClassSet();*/
		    for(Class cls:clsSet){
		    	Object instance = null;
				try {
					instance = cls.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	beanMap.put(cls, instance);
		    }
		    // 处理beanMap
		    for(Class<?> cls:controlerBean){
		    	Object instance=beanMap.get(cls);
		    	// 对字段进行ioc
	            Field fields[]=cls.getDeclaredFields();
	            // 看字段是否进行了注解
	            for(Field field:fields){
	            	if(field.isAnnotationPresent(Inject.class)){
	            		field.setAccessible(true);
	            		// 获取Inject的实例
	            		// 获取字段的类
	            		Class clsField=field.getType();
	            		
	            		Object value=beanMap.get(clsField);
	            		try {
							field.set(instance,value);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}
	            	
	            }
		    }
	}
	 
	 
	 
	 
	 
}
