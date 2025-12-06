package dsa.practice.lld.parkingLot.payment;

public class PaymentStrategy {
    private final PaymentMethod paymentMethod;

    public PaymentStrategy(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    void pay(double amount){
        paymentMethod.pay(amount);
    }
}
