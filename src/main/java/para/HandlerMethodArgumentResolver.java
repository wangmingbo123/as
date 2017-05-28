package para;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

// 处理参数的接口
public interface HandlerMethodArgumentResolver {
	    boolean supportsParameter(MethodParameter parameter);
	
	
	   // 根据container从request里面取得参数，
	   // 并放到container里面
	
       Object resolve(MethodParameter parameter,Container container,HttpServletRequest request);
}
