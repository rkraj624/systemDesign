package dsa.practice.lld.designPattern.visitor;

public class Triangle implements Shape{
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
