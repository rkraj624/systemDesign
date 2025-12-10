package dsa.practice.springBoot.interceptor;
import dsa.practice.springBoot.annotation.RequestInterceptor;
import dsa.practice.springBoot.enums.Role;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
@Aspect
public class BeforeControllerInterceptor {
    @Around("@annotation(dsa.practice.springBoot.annotation.RequestInterceptor)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before controller method execution");
        
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequestInterceptor annotation = method.getAnnotation(RequestInterceptor.class);
        
        Role[] role = annotation.role();
        if(role.length == 0){
            throw new IllegalArgumentException("Role is not specified");
        }
        
        System.out.println("Required roles: " + java.util.Arrays.toString(role));
        
        Object result = joinPoint.proceed();
        System.out.println("After controller method execution");
        return result;
    }
}
