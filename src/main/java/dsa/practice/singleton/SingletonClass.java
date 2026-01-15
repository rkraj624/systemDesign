package dsa.practice.singleton;

public class SingletonClass {
    private static final SingletonClass INSTANCE = new SingletonClass();

    private SingletonClass() {
    }

    public static SingletonClass getInstance(){
        return INSTANCE;
    }

    public static void printClassName(){
        System.out.println(INSTANCE.getClass().getSimpleName());
    }
}
