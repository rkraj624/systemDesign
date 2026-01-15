package dsa.practice.lld.designPattern.visitor;

class Rectangle implements Shape {
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
