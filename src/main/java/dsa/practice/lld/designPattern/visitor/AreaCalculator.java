package dsa.practice.lld.designPattern.visitor;

class AreaCalculator implements ShapeVisitor {

    @Override
    public void visit(Circle circle) {
        System.out.println("Calculating circle area");
    }
    @Override
    public void visit(Rectangle rectangle) {
        System.out.println("Calculating rectangle area");
    }

    @Override
    public void visit(Triangle triangle) {
        System.out.println("Calculating triangle area");
    }
}
