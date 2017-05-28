package work;

import org.Book;

import annotation.Action;
import annotation.Entity;
import annotation.Inject;
import annotation.ModelAttribute;
import proxy.Controller;

@Controller
public class UserControl {
     @Inject
	 User inUser;
	
	 @ModelAttribute("address")
	 public String name() {
		    // 在moddel里面修改
		     System.out.println("在moddel里面修改 ");
		     inUser.setUserName("bobobo");
		 
		    return "huibei";
	}
	   
	 @Action(method = "post", url = "/get", produceType = "application/json")
     String testReturn(String userName,Integer userId){
  	      System.out.println("userName "+userName);
  	      System.out.println("userId "+userId);
  	      // 能自动进行类型转化
  	     
  	      User user=new User();
  	      user.userId=1;
  	      user.userName="wang";
  	      return "ok";
     }
	 
	 @Action(method = "post", url = "/get1", produceType = "application/json")
     Book testArgBean(User user,String userName){
		  System.out.println("在目标方法里面查看");
		  System.out.println("inUser "+inUser.userName);
		 
//  	      System.out.println("userName "+userName);
//  	      System.out.println("userId "+userId);
  	      // 能自动进行类型转化
  	     
//  	      User user=new User();
//  	      user.userId=1;
//  	      user.userName="wang";
		 if(user!=null)
		 System.out.println("bean userName "+user.userName);
		 System.out.println("userId  "+user.userId);
		 System.out.println("single "+userName);
  	      return new Book();
     }
}
