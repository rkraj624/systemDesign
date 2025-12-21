package dsa.practice.lld.bookMyShow.payment;

public interface PaymentGateway {
    PaymentGatewayResult charge(String paymentId, int amount);
}
