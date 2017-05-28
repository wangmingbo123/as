import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import work.User;

public class App {
       public static void main(String[] args) {
		      Class cls=User.class;
		      Method method=cls.getMethods()[2];
		      LocalVariableTableParameterNameDiscoverer discoverer=new LocalVariableTableParameterNameDiscoverer();
		      String parameterANames[]=discoverer.getParameterNames(method);
		      for(String para:parameterANames)
		    	  System.out.println(para);
		      
		      //Object ui=BeanUtils.instantiate(Integer.class);
		      // 借助simple Type Converter 
		      SimpleTypeConverter simpleTypeConverter=new SimpleTypeConverter();
		      Object arg=5;
		      Object bian=simpleTypeConverter.convertIfNecessary(arg,Integer.class);
		      System.out.println("class type "+bian.getClass());
		      Object yu=(Integer)arg;
		      System.out.println("cls type "+yu.getClass());
		      
		      
	}
       
       
}
