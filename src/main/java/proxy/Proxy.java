package proxy;

// 加入aop性质



public interface Proxy {
       public Object doProxy(ProxyChain proxyChain) throws Throwable; 
}
