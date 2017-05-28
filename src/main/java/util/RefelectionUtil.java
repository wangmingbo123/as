package util;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 反射工具
// 生成实例
// get field value
// set field value


public class RefelectionUtil {
       public static Object newInstance(Class<?> cls) {
		      Object instance=null;
		      try {
				instance=cls.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      return instance;
	}   
	// 调用方法
    // 消去try-catch代码
    public static Object invokeMethod(Method method,Object target,Object ...args) {
		   Object returnValue=null;
		   try {
			   returnValue=method.invoke(target, args);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return returnValue;
	}   
       
       
       
       
       
       
       
}
