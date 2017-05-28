package annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InitBinder {
       // 一个control 可能会有多种绑定
	   // 在调用目标方法时，先执行
	   String [] value() default {};
	   
}
