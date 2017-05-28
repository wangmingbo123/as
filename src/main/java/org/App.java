package org;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import loader.WebClassLoader;

public class App {
	   @Test
       public void name() throws IOException {
		      FileOutputStream fileOutputStream=new FileOutputStream("src/main/java/wang");
		      fileOutputStream.write("wang".getBytes());
	  }
	  @Test 
	  public void app() {
		     File file=new File("src/main/java");
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
		    		    System.out.println("className "+className);
		    		    
		    		    WebClassLoader.loadClass(className);
		    		    
		    		 }
		    		  
		    	  }	
		    		
		    	}
		    	 
		     }
	} 
	   
	   
	   
	   
}
