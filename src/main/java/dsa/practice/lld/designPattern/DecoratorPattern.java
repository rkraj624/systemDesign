package dsa.practice.lld.designPattern;

public class DecoratorPattern {
    public static void main(String[] args) {
        Pizza pizza = new Margherita();
        pizza = new Cheese(pizza);
        pizza = new Panner(pizza);

        System.out.println(pizza.getDescription());
        System.out.println("Total Cost: ₹" + pizza.getCost());
    }
}

interface Pizza{
    String getDescription();
    double getCost();
}

class Margherita implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200;
    }
}

class FarmHouse implements Pizza {
    @Override
    public String getDescription() {
        return "FarmHouse Pizza";
    }

    @Override
    public double getCost() {
        return 300;
    }
}

abstract class ToppingDecorator implements Pizza{
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class Cheese extends ToppingDecorator{

    public Cheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " with Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 50;
    }
}

class Panner extends ToppingDecorator{

    public Panner(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " with Panner";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 70;
    }
}


