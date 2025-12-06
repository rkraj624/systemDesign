package dsa.practice.lld.solidPrinciple;

/**
* Difference between LSP & ISP
* */


public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {

    }
}

interface RestaurantWorker{
    void washDish();
    void cook();
    void serve();
}

/**
* This is incorrect Waiter class is forced to implement methods which is of no uses.
* */

class Waiter implements RestaurantWorker{

    @Override
    public void washDish() {
        throw new RuntimeException("This is not my job.");
    }

    @Override
    public void cook() {
        throw new RuntimeException("This is not my job.");
    }

    @Override
    public void serve() {
        System.out.println("Dish has been served properly to client.");
    }
}

interface Waiters{
    void serve();
}

interface Cook{
    void cook();
}

interface DishClean{
    void wash();
}
/**
* Here we have segregated or grouped similar methods to an interface
 * */
class WaiterEmployee implements Waiters{

    @Override
    public void serve() {
        System.out.println("Dish has been served properly to client.");
    }
}

class CookEmployee implements Cook{

    @Override
    public void cook() {

    }
}

class DishCleanerEmployee implements DishClean{

    @Override
    public void wash() {

    }
}