package org;
import java.util.EventObject;

import javax.servlet.http.HttpServletRequest;

public class RequestEvent extends EventObject{
	   HttpServletRequest source; 

	public RequestEvent(HttpServletRequest source) {
		   super(source);
           this.source=source;
	}

	public HttpServletRequest getSource() {
		return source;
	}
	

}
