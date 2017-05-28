package para;

import java.util.HashMap;
import java.util.Map;

// 容器
// 用于保存参数值
public class Container {
	    Map<String,Object> map=new HashMap<String, Object>();
	    public void addAttribute(String key,Object value) {
	    	   map.put(key, value);
			
		}
     
}
