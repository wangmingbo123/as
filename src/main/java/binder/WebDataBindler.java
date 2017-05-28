package binder;
import java.sql.Timestamp;

public class WebDataBindler {
       //目前支持将string->TimeStamp类型
	   
	  /* public Object convert(Class<?> cls,Object object) {
		      
	   }*/
	   
	   // 目标类型
	   Class<?> cls;
	   public Object convert(Object object) {
		             //如果是timestamp类型
		             //将其转化为long类型
		      Object instance=null;
		      if(object.getClass().equals(Timestamp.class)){
		                  	 
		            	 
		       }
               return (Long)object;
		      
	}
	public void addTypeConvert() {
		
	}   
	   
	   
}
