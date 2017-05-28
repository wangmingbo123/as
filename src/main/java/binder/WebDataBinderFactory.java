package binder;

public interface WebDataBinderFactory {
       // create WebDataBinder
	   WebDataBindler createDataBinder(String requestUrl,Object target,String objectName);
}
