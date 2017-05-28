package org;
import java.util.EventListener;

public interface RequestListener extends EventListener{
	    // 请求销毁监听
        public void requestDestroyed(RequestEvent re);
			
		
        // 请求初始化
        
        public void requestInitialized(RequestEvent re);
}
