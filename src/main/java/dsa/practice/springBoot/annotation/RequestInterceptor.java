package dsa.practice.springBoot.annotation;
import dsa.practice.springBoot.enums.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestInterceptor {
    Role[] role() default {Role.ADMIN, Role.SUPER_ADMIN};
}
