package dsa.practice.lld.designPattern.visitor;

public class NewBusinessLogic extends AreaCalculator{
    @Override
    public void visit(Circle circle) {
        System.out.println("New Business Logic for circle");
    }
}
