package dsa.practice.lld.designPattern.visitor;

class Circle implements Shape {
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}