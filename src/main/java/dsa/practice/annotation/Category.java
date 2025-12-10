package dsa.practice.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Categories.class)
public @interface Category {
    String[] diet() default {"CARNIVORE", "OMNIVORE", "HERBIVORE"};
    String animalType() default "ANIMAL";
}
