package loader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class WebClassLoader {
	   public static void print() {   
		      try {
		    	// 获取classpath 路径下的所有资源  
				Enumeration<URL> urls=classLoader.getResources("");
				while (urls.hasMoreElements()) {
					URL url = urls.nextElement();
					System.out.println("classpath "+url.toString());
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		
	} 
	
       static ClassLoader classLoader;
       public static ClassLoader getClassLoader() {
		return classLoader;
	}
	public static void setClassLoader(ClassLoader classLoader) {
		WebClassLoader.classLoader = classLoader;
	}
	static{
    	   //classLoader=Thread.class.getClassLoader();
    	   classLoader=Thread.currentThread().getContextClassLoader();
    	   
       }
       public WebClassLoader() {
              
              
       }
       public static Class<?> loadClass(String className) {
		      try {
				return classLoader.loadClass(className);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return null;  
		      
	}
    // 获取指定包下面的所有类
       public static Set<Class<?>> getClasses1(String packageName) {
    	     
    	      print();
    	      URL url=classLoader.getResource("org");
    	      
    	      System.out.println("url "+url.getPath());
    	      //url=classLoader.getResource("lib");
    	      //System.out.println("lib "+url.getPath());
    	      // 父类用于加载  
    	      ClassLoader parent=classLoader.getParent();
    	      try {
    	    	  
				Enumeration<URL> urls=parent.getResources("org");
				while(urls.hasMoreElements()){
					 URL one=urls.nextElement();
					 System.out.println("one "+one.toString());
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      Set<Class<?>> classSet=new HashSet<Class<?>>();
		      File file=new File(packageName);
		      System.out.println("file is "+file.getName());
		      System.out.println("abs is "+file.getAbsolutePath());
		      
			     if(file.isDirectory()){
			    	// 获取该包下面的所有文件
			    	File files[]=file.listFiles();
			    	for(File f:files){
			    	  // 如果是目录，则跳过
			    	  // 是文件,则继续	
			    	  if(f.isFile()){
			    		 //System.out.println("file name "+f.getName());
			    		 int index=f.getName().indexOf(".");
			    		 if(index!=-1){
			    		    String className=f.getName().substring(0,index);
			    		    // 包名+类名
			    		    //className=packageName+"."+className;
			    		    System.out.println("className "+className);
			    		    
			    		    Class<?> cls;
							try {
								//cls = Class.forName("com."+className,false,classLoader);
								cls=Class.forName(packageName+"."+className);
								classSet.add(cls);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		    
			    		 }
			    		  
			    	  }	
			    		
			    	}
			    	 
			     }
		      return classSet;
	}
    // 获取类加载器
       
       
       
    public static Set<Class<?>> getClasses(String packageName) {
    	Set<Class<?>> classSet=new HashSet<Class<?>>();
    	try {
			Enumeration<URL> urls=classLoader.getResources(packageName);
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				//System.out.println("url "+url.getFile());
				//System.out.println("protocol "+url.getProtocol());
				if(url.getProtocol().equals("file")){
				  // file协议
			      // 在web-inf/classes/目录下
			      File file=new File(url.getFile());
			      if(file.isDirectory()){
			    	  for(File f:file.listFiles()){
			    		  System.out.println("class name "+f.getName());
			    		  doAddclass(classSet, f, packageName);
			    		  
			    		  
			    	  }
			      }
					
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return classSet;
	}   
    // add class to classSet   
    public static void doAddclass(Set<Class<?>> classSet,File f,String packageName) {
    	// 是文件,则继续	
  	  if(f.isFile()){
  		 //System.out.println("file name "+f.getName());
  		 int index=f.getName().indexOf(".");
  		 if(index!=-1){
  		    String className=f.getName().substring(0,index);
  		    // 包名+类名
  		    //className=packageName+"."+className;
  		    System.out.println("className "+packageName+"."+className);
  		    
  		    Class<?> cls;
				try {
					cls = Class.forName(packageName+"."+className,false,classLoader);
					//cls=Class.forName(packageName+"."+className);
					classSet.add(cls);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  		 }
  		 }
  		
	}   
    
    
    
       
}
