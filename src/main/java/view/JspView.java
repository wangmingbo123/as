package view;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements View{
       String viewType;
       Map<String,Object> model=new HashMap<String, Object>(); 
	   // 返回的jsp路径
       String path;
	public JspView(String viewType) {
		super();
		this.viewType = viewType;
	}


	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) {
		
	}


	public View addModel(String key, Object value) {
		   model.put(key, value);
		   return this;
	}
	
	public View setPath(String path) {
		   this.path=path;
		   return this;
	}


	public Map<String, Object> getModel() {
		return model;
	}
	
	
	
	

}
