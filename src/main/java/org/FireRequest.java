package org;
import javax.servlet.http.HttpServletRequest;

public interface FireRequest {
	   public void fireRequestInitEvent(HttpServletRequest request);
	   public void fireRequestDestroyEvent(HttpServletRequest request);

}
