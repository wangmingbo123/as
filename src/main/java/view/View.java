package view;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface View {
       public  void render(Map<String,?> model,HttpServletRequest request,HttpServletResponse response);
       public  View addModel(String key,Object value);
}
