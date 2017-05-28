package org;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;

import annotation.ModelAttribute;
import handler.Handler;
import handler.HandlerExecutionChain;
import help.BeanHelper;
import help.ClassHelper;
import help.ConfigHelp;
import help.ControllerHelp;
import help.IocHeapler;
import model.ModelAndView;
import model.ModelFactory;
import para.Container;
import para.InvocableHandlerMethod;
import proxy.App;
import util.JsonUtil;
import util.RefelectionUtil;
import view.JsonView;
import view.JspView;
@WebServlet(loadOnStartup=0,urlPatterns="/*")
public class DispatcherServlet extends HttpServlet implements FireRequest{
	public ControllerHelp controllerHelp=null;
	public IocHeapler iocHeapler=null;
	public ClassHelper classHelper=null;
	public HandlerExecutionChain handlerExecutionChain=new HandlerExecutionChain();
	
	public List<RequestListener> requestListeners=new ArrayList<RequestListener>();
	
	// 请求属性监听
    public List<RequestAttributeListener> requestAttributeListeners=new ArrayList<RequestAttributeListener>();
    // 一个cls controller 包含的(@moddel的方法个数)method个数
    
    Map<Class<?>,Set<Method>> modelAttribute=new ConcurrentHashMap<Class<?>, Set<Method>>();
    
    
	
    
	@Override
	public void init() throws ServletException {
		    //String packageName=ConfigHelp.getString("basePackage"); 
		    //classHelper=new ClassHelper(packageName);
		    controllerHelp=new ControllerHelp();
		    //System.out.println(controllerHelp.toString());
		    
		    iocHeapler=new IocHeapler();
		    iocHeapler.controlerBean=controllerHelp.classHelper.getClassController();
		    iocHeapler.clsSet=controllerHelp.classHelper.classSet;
		    iocHeapler.beginIoc();
		    // 注册拦截器
		    handlerExecutionChain.addInterceptor(new LogIntercetor());
		    
		    // 注册监听
		    requestListeners.add(new HelloRequestListener());
		    
		    
		    
		    
		    
		    
		    
		
	}
	public void startProxy() {
		// 扫描注解,实现了aop  目前拦截所有方法
	    // com包下
	    App app=new App();
	    app.main();
	    // 进行了代理
	    Map<Class<?>,Object> beanMap=BeanHelper.beanMap;
	    
	    
	    
	    // 替换
	    for(InvocableHandlerMethod invocableHandlerMethod:controllerHelp.requestAndHandlerMethod.values()){
	        Class cla=invocableHandlerMethod.getBeanType();
	        if(beanMap.containsKey(cla)){
	        	invocableHandlerMethod.setBean(beanMap.get(cla));
	        	
	        }
	    	
	    }
	    
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		            
		      // 触发初始化事件
		      fireRequestInitEvent(req);
		
		      // 根据req 得到url;
		      // 根据url 找到hangder
		      // 得到controller+method
		      // 采用反射执行目标方法
		       String url=req.getPathInfo();
		       // ContextPath 项目名称  spring-mvc
		       System.out.println("request url "+req.getContextPath());
		       Handler handler=controllerHelp.getHandler(url);
		       Class<?> controll=handler.getControllerClass();
		       Object target=RefelectionUtil.newInstance(controll);
		       // 拦截器处理前
		       for(int i=0;i<handlerExecutionChain.size();i++){
		    	   handlerExecutionChain.applypreHandler(req, resp);
		    	   
		       }
//		       Object arg=new Object();
		       ModelAndView modelAndViewAsArg=new ModelAndView(this,url);
		       // 事件源
		       modelAndViewAsArg.request=req;
		       // 增加ModelAndView用于参数,以此来实现监听着模式
		       Object view=null;
		       //Object view=RefelectionUtil.invokeMethod(handler.getMethod(), target,modelAndViewAsArg);
		       
	           // 提供参数解析
		       // 继承类
               InvocableHandlerMethod invocableHandlerMethod=controllerHelp.getHandlerUpdate(url);
               
               //设置bean 
               // ioc
               invocableHandlerMethod.setBean(iocHeapler.getBean(handler.getControllerClass()));
               
               

 		      ModelFactory modelFactory=getModelFactory(invocableHandlerMethod);
 		    
 		      
 		      
 		      
               // 容器用于存放传递参数
               Container container=new Container();
               modelFactory.initModel(req, container);  
               
               
//			   view=invocableHandlerMethod
               invocableHandlerMethod.invokeAndHandle(req, resp, container);
               // view 单独创建
               
               
		       
		       for(int i=0;i<handlerExecutionChain.size();i++){
		    	   handlerExecutionChain.applypostHandle(req, resp);
		    	   
		       }
		       // 拦截器处理后
		
		      // 处理返回结果
		      // 1 jsp
		      // 2 json
		      if(view instanceof JspView){
		    	 Map<String,Object> model=((JspView) view).getModel();
		    	 for(String key:model.keySet()){
		    		 Object value=model.get(key);
		    		 req.setAttribute(key, value);
		    		 
		    		 
		    	 }
		    	 // 进行转发
		    	 req.getRequestDispatcher("index.jsp").forward(req, resp);
		      }
		      
		      // json格式
              if(view instanceof JsonView){
		    	  //
                  String json=JsonUtil.toJson(((JsonView) view).getModel());
		          resp.setContentType("application/json");
		          PrintWriter writer=resp.getWriter();
		          writer.write(json);
		          writer.flush();
		          writer.close();
              }
		      
		      // 拦截器完成 
		       
              for(int i=0;i<handlerExecutionChain.size();i++){
		    	   handlerExecutionChain.applyafterCompletion(req, resp);
		    	   
		       }
		       
		
		       
              
              // 第二点  
              // 添加监听事件
              //  1 事件类型
              //  request
              //  1 request 1.1 初始化  1.2 销毁
              //  2 request 的监听
              //  session
              //  1  创建    销毁
              //  2  session属性的添加和销毁
              
              //  3 content 对应的web程序
              // 
              
              
              
              
              
              
              
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	// 初始化事件
	public void fireRequestInitEvent(HttpServletRequest request) {
           RequestEvent requestEvent=new RequestEvent(request);
		   for(int i=0;i<requestListeners.size();i++){
        	   RequestListener listener=requestListeners.get(i);
        	   listener.requestInitialized(requestEvent);
           }   
		 
		
	}

	// 销毁事件
	public void fireRequestDestroyEvent(HttpServletRequest request) {
		 RequestEvent requestEvent=new RequestEvent(request);
		   for(int i=0;i<requestListeners.size();i++){
      	   RequestListener listener=requestListeners.get(i);
      	   listener.requestDestroyed(requestEvent);
         }   
		
	}
	
    //获得modelFactory
	public ModelFactory getModelFactory(InvocableHandlerMethod handlerMethod) {
		   Map<Class<?>,Set<Method>> modelAttribute=new HashMap<Class<?>, Set<Method>>();
		   List<InvocableHandlerMethod> attrsMethod=new ArrayList<InvocableHandlerMethod>();
			  
		   Set<Method> methods=new HashSet<Method>();
		   Class<?> beanType=handlerMethod.getBeanType();
		   for(Method method:beanType.getMethods()){
			   if(method.isAnnotationPresent(ModelAttribute.class)){
//				   modelAttribute.put(beanType,method);
				   methods.add(method);
				   attrsMethod.add(create(beanType, method,handlerMethod.getBean()));
			   }
		   }
		   modelAttribute.put(beanType, methods);
		 
		   // build  工厂
		   
		   return new ModelFactory(attrsMethod);
		   
		   
		
	}
	public InvocableHandlerMethod create(Class clsBean,Method method,Object bean) {
		   // 严重bug
		   InvocableHandlerMethod handlerMethod=new InvocableHandlerMethod(clsBean, method);
		   // setBean 和setBeanCls
		   handlerMethod.setBean(bean);
		   return handlerMethod;
	}
	
	
	
	
	
	
	
	

	
	

}
