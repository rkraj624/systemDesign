package dsa.practice.lld.bookMyShow.payment;

import java.util.Objects;
import java.util.Random;

public class MockPaymentGateway implements PaymentGateway {
    private final Random random;

    public MockPaymentGateway(Random random) {
        this.random = Objects.requireNonNull(random);
    }

    @Override
    public PaymentGatewayResult charge(String paymentId, int amount) {
        boolean ok = random.nextBoolean();
        return new PaymentGatewayResult(ok);
    }
}
