package para;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 处理返回值
public interface HandlerMethodReturnValueHandler {
	   public void handleReturnValue(Object returnValue,MethodParameter parameter,Container container,HttpServletResponse response);
}
