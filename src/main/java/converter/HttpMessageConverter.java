package converter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 将object write to the output message
public interface HttpMessageConverter<T> {
       
	   T read(Class<? extends T> clazz,HttpServletRequest request);
	   void write(T returnValue,HttpServletResponse response);
	
	
}
