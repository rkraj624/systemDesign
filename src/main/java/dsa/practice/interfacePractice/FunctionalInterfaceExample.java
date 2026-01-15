package dsa.practice.interfacePractice;

@FunctionalInterface
public interface FunctionalInterfaceExample extends NonFunctionalInterfaceExample{
    void print(String s);
}

@FunctionalInterface
interface BirdFly extends FunctionalInterfaceExample{
    void print(String s);
}

interface NonFunctionalInterfaceExample {
    default void print(String s, String s1){
        System.out.println(s+" World");
    }
}




/**
 * Implementing the interface
 * <p>We can implement the interface in 3 ways</p>
 * <p>1. Implementing the interface</p>
 * <p>2. Lambda Expression</p>
 * <p>3. Method Reference</p>
 */
class MainInterface implements FunctionalInterfaceExample{
    public static void main(String[] args) {

        FunctionalInterfaceExample functionalInterfaceImplementation = new FunctionalInterfaceExample() {
            @Override
            public void print(String s) {
                System.out.println(s+" World");
            }
        };
        functionalInterfaceImplementation.print("Hello");

        /*
        * Lambda Expression
        */
        FunctionalInterfaceExample functionalInterfaceExample = (String s) -> System.out.println(s+" World");
        functionalInterfaceExample.print("Hello");
    }


    @Override
    public void print(String s) {
        System.out.println(s+" World");
    }
}