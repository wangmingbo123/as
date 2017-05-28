package view;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonView implements View{
	// view 类型代码
	// 在jsonView和jspView都有  
	//  可以提取出来
	 String viewType;
     Map<String,Object> model=new HashMap<String, Object>(); 
		
		public JsonView(String viewType) {
			
			this.viewType = viewType;
		}
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) {
  		
	}
	
	public View addModel(String key, Object value) {
		 model.put(key, value);
		   return this;
	}
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	
	
	
	
	
	
	

}
