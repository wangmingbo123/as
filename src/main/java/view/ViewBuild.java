package view;

public class ViewBuild {
	static View view=null;   
	public static View buildView(String viewName) {
		if(viewName==null){
		   return null;	
		} 
		if(viewName.equals("jsp")){
			view=new JspView("jsp");
		}else if(viewName.equals("json")){
			view=new JsonView("json");
		}
		return view;
	}

}
