package org;
import javax.servlet.http.HttpServletRequest;

public class RequestAttributeEvent extends RequestEvent{

	String name;
	Object value;
	String source;
	public RequestAttributeEvent(HttpServletRequest request,String url,String name,Object value) {
		super(request);
		
		// 请求路径做事件源
		this.source=url;
		this.name=name;
		this.value=value;
	}
    
	
}
