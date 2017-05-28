package converter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

public abstract class AbstractHttpMessageConverter<T> implements HttpMessageConverter<T> {

	MediaType mediaType;
	public T read(Class<? extends T> clazz, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public void write(T t, HttpServletResponse response) {
	       writeInternal(t, response);
	       
	       try {
			response.getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}
	// 
    public abstract void writeInternal(T t, HttpServletResponse response); 
		
	
	
	
}
