package dsa.practice.lld.designPattern.visitor;

public class Main {
    public static void main(String[] args) {
        Shape shape = new Circle();
        shape.accept(new NewBusinessLogic());
        shape = new Rectangle();
        shape.accept(new NewBusinessLogic());

    }
}
