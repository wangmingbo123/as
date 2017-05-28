package converter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class MappingJackson2HttpMessageConverter extends AbstractHttpMessageConverter<Object>{
    ObjectMapper objectMapper=new ObjectMapper(); 
	
	@Override
	public void writeInternal(Object t, HttpServletResponse response) {
		   // 
		   response.setContentType("application/json");
		   response.setCharacterEncoding("utf-8");
           JsonEncoding jsonEncoding=JsonEncoding.UTF8;
           try {
			JsonGenerator  jsonGenerator=objectMapper.getJsonFactory().
					   createGenerator(response.getOutputStream(), jsonEncoding);
			// 写入数据
			objectMapper.writeValue(jsonGenerator, t);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
		
	}
    
	
	
}
