package help;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.regexp.internal.recompile;

import annotation.Entity;
import loader.WebClassLoader;
import proxy.Aspect;
import proxy.Controller;

// webclassloader用于加载类
// 而ClassHelper 则用于从加载的类中找到不同的类

public class ClassHelper {
       
	   public static Set<Class<?>> classSet=new HashSet<Class<?>>();   
	   public ClassHelper(String packageName) {
              classSet=WebClassLoader.getClasses(packageName); 
	   }
	   
	   
	   // 获取所有@Controlller类
	   public static Set<Class<?>> getClassController() {
		      Set<Class<?>> clses=new HashSet<Class<?>>();
		      for(Class cls:classSet){
		    	  if(cls.isAnnotationPresent(Controller.class)){
		    		  //如果注解了control
		    		  clses.add(cls);
		    	  }
		      }
		      return clses;
		   
		   
	  }
	   
	   public static Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> annotationClass) {
		      Set<Class<?>> clses=new HashSet<Class<?>>();
		      for(Class cls:classSet){
		    	  if(cls.isAnnotationPresent(annotationClass)){
		    		  //如果注解了control
		    		  clses.add(cls);
		    	  }
		      }
		      return clses;
		   
		   
	  }
	  
	   public static Set<Class<?>> getClass(Class acls) {
		      Set<Class<?>> clses=new HashSet<Class<?>>();
		      for(Class cls:classSet){
		    	  if(cls.isAnnotationPresent(acls)){
		    		  //如果注解了control
		    		  clses.add(cls);
		    	  }
		      }
		      return clses;
		   
		   
	  } 
	   
	  @Override
	public String toString() {
		   for(Class<?> cls:classSet){
			   System.out.println(cls.getName());
			   
		   }
		   
		  
		  
		return super.toString();
	}
	public void print(Map<Class<?>,Set<Class<?>>> map) {
		   for(Map.Entry<Class<?>,Set<Class<?>>> entry:map.entrySet()){
			   
		   }
		
	}  
	
	
	// contain @controller and @Inject 
	public static Set<Class<?>> getClassSet() {
		// 可插拔式
		Set<Class<?>> clses=new HashSet<Class<?>>();
		clses.addAll(getClassByAnnotation(Controller.class));
//		clses.addAll(getClassByAnnotation(Inject.class));
		// 实体类注解
		clses.addAll(getClassByAnnotation(Entity.class));
		return clses;
	}
	
	   
  
	   
	   
	   
	
}
