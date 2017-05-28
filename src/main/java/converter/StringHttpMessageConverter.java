package converter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

public class StringHttpMessageConverter extends AbstractHttpMessageConverter<String>{
       
	Charset charset=Charset.forName("utf-8");
	@Override
	public void writeInternal(String t, HttpServletResponse response) {
		   try {
			FileCopyUtils.copy(t,new OutputStreamWriter(response.getOutputStream(), charset));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
	}
	  

}
