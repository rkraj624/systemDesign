package dsa.practice.lld.bookMyShow.repository;

import dsa.practice.lld.bookMyShow.model.PaymentTransaction;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PaymentRepository {
    private final ConcurrentMap<String, PaymentTransaction> paymentsById = new ConcurrentHashMap<>();

    public void save(PaymentTransaction paymentTransaction) {
        Objects.requireNonNull(paymentTransaction);
        paymentsById.put(paymentTransaction.getPaymentId(), paymentTransaction);
    }

    public Optional<PaymentTransaction> findById(String paymentId) {
        return Optional.ofNullable(paymentsById.get(paymentId));
    }

    public Optional<PaymentTransaction> findByBookingId(String bookingId) {
        for (PaymentTransaction pt : paymentsById.values()) {
            if (pt.getBookingId().equals(bookingId)) {
                return Optional.of(pt);
            }
        }
        return Optional.empty();
    }
}
