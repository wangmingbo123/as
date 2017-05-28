package help;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import loader.WebClassLoader;

public class ConfigHelp {
	 static String configFileName="config.properties";
       public static String getString(String key){
    	      String value=null;
    	      InputStream inputStream=WebClassLoader.getClassLoader().getResourceAsStream(configFileName);
    	      Properties properties=new Properties();
    	      try {
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	      return properties.getProperty(key);
       } 
}
