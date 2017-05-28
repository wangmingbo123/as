package help;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanHelper {
	   public static Map<Class<?>,Object> beanMap=new HashMap<Class<?>, Object>();
	   public static void constructor(Set<Class<?>> classes) {
		       for(Class<?> cls:classes){
		    	   try {
					Object object=cls.newInstance();
					beanMap.put(cls,object);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	       
		       }
		       
		       
		
	}
	public static void set(Class<?> cls,Object object) {
		beanMap.put(cls,object);
	}   
	
    public static Object getBean(Class<?> beanName) {
		    return beanMap.get(beanName);
	}	


}
