package org;

public class HelloRequestAttributeListener implements RequestAttributeListener{

	public void attributeAdded(RequestAttributeEvent rae) {
           System.out.println("name "+rae.name);
           System.out.println("value "+rae.value);
	}

	public void attributeRemoved(RequestAttributeEvent rae) {
		System.out.println("name "+rae.name);
        System.out.println("value "+rae.value);
	}

	public void attributeReplaced(RequestAttributeEvent rae) {
		System.out.println("name "+rae.name);
        System.out.println("value "+rae.value);
	}

}
