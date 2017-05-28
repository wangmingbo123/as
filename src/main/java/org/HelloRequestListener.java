package org;

public class HelloRequestListener implements RequestListener{

	public void requestDestroyed(RequestEvent re) {
		System.out.println("request destroy");
		
	}

	public void requestInitialized(RequestEvent re) {
		System.out.println("request initial");	
		
	}

}
