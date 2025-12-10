package dsa.practice.reflections;

import dsa.practice.annotation.Category;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

//@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {


        Class<?> aClass = Class.forName("dsa.practice.reflections.Eagle");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true); // to access private constructor
        Object egaleObject = declaredConstructor.newInstance();
        Method method = aClass.getMethod("fly", String.class, boolean.class, boolean.class);
        method.invoke(egaleObject, "Bald Eagle", true, false);
        System.out.println("--------------------------------------------------------");
        Field breed = aClass.getDeclaredField("breed");
        breed.setAccessible(true);
        breed.set(egaleObject, "Bald Eagle");
        Field canSwim = aClass.getDeclaredField("canSwim");
        canSwim.setAccessible(true); // to access private fields/methods
        canSwim.set(egaleObject, true);
        Field canFly = aClass.getDeclaredField("canFly");
        canFly.set(egaleObject, true);
        System.out.println(egaleObject);
        Eagle eagle = new Eagle();
        eagle.fly("Bald Eagle", true, false);

        Category[] categories = aClass.getAnnotationsByType(Category.class);
        for (Category category : categories) {
            System.out.println(Arrays.toString(category.diet()));
            System.out.println(category.animalType());
            System.out.println("--------------------------------------------------------");
        }



/*
        System.out.println(eagleClass.getSimpleName());
        System.out.println(eagleClass.getModifiers());

        Method[] methods = eagleClass.getMethods();
        Method[] methods = eagleClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method name: " + method.getName());
            System.out.println("method return type: " + method.getReturnType().getName());
            System.out.println("method modifiers: " + method.getModifiers());
            System.out.println("method parameters: " + method.getParameterCount());
            System.out.println("--------------------------------------------------------");
        }
*/
    }
}
