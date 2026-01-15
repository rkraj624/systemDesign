package dsa.practice.lld.designPattern.visitor;

public interface Shape {
    void accept(ShapeVisitor visitor);
}
