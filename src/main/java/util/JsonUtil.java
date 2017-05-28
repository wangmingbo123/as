package util;
import com.google.gson.Gson;

public class JsonUtil {
	   // obj->json
       public static <T>String toJson(T obj) {
		      String json=null;
		      Gson gson=new Gson();
		      json=gson.toJson(obj);
		      return json;
	   }
       // json->obj
       public static <T> T  fromJson(String json,Class<T> type) {
		     T pojo=null;
		     pojo=new Gson().fromJson(json, type);
		     return pojo;
	}
       
       
}
