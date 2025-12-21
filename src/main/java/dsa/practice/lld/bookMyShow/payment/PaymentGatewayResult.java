package dsa.practice.lld.bookMyShow.payment;

public class PaymentGatewayResult {
    private final boolean success;

    public PaymentGatewayResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
