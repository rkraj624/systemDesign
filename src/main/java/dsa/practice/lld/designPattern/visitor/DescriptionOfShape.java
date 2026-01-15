package dsa.practice.lld.designPattern.visitor;

public class DescriptionOfShape implements ShapeVisitor{
    @Override
    public void visit(Circle circle) {
        System.out.println("Circle is round in shape");
    }

    @Override
    public void visit(Rectangle rectangle) {
        System.out.println("Rectangle is square in shape but different in length and width");
    }

    @Override
    public void visit(Triangle triangle) {
        System.out.println("Triangle is triangle in shape with 3 sides closed");
    }
}
