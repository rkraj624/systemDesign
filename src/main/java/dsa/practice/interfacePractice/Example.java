package dsa.practice.interfacePractice;

public interface Example {
    default void print(){
        printPrivate();
        System.out.println("this is the concrete default method of interface");
    }

    private void printPrivate(){
        printPrivateStatic();
        printStatic();
        print();
        System.out.println("this is the concrete private method of interface");
    }

    private static void printPrivateStatic(){
        System.out.println("this is the concrete private-static method of interface");
    }

    static void printStatic(){
        printPrivateStatic();
        System.out.println("this is the concrete static method of interface");
    }

    void call();
}

class Practice implements Example{

    @Override
    public void call() {
        System.out.println("this is the implemented method from interface Example");
    }

    public static void main(String[] args) {
        Example example = new Practice();
        example.print();
        Example.printStatic();

        Example obj = new Example() {
            @Override
            public void call() {
                System.out.println("Anonymous Object");
            }
        };
        obj.print();
    }
}