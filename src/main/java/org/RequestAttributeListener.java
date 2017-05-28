package org;
import java.util.EventListener;

public interface RequestAttributeListener extends EventListener{
	   public void attributeAdded(RequestAttributeEvent rae);
	   public void attributeRemoved(RequestAttributeEvent rae);
	   public void attributeReplaced(RequestAttributeEvent rae);
	

}
