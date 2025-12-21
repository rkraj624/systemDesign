package dsa.practice.lld.bookMyShow.service;

import dsa.practice.lld.bookMyShow.enums.PaymentStatus;
import dsa.practice.lld.bookMyShow.model.PaymentTransaction;
import dsa.practice.lld.bookMyShow.payment.PaymentGateway;
import dsa.practice.lld.bookMyShow.payment.PaymentGatewayResult;
import dsa.practice.lld.bookMyShow.repository.PaymentRepository;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentRepository paymentRepository, PaymentGateway paymentGateway) {
        this.paymentRepository = Objects.requireNonNull(paymentRepository);
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    public PaymentTransaction initiatePayment(String bookingId, int amount) {
        Objects.requireNonNull(bookingId);

        Optional<PaymentTransaction> existing = paymentRepository.findByBookingId(bookingId);
        if (existing.isPresent()) {
            return existing.get();
        }

        String paymentId = UUID.randomUUID().toString();
        PaymentTransaction tx = new PaymentTransaction(paymentId, bookingId, amount, Instant.now());
        paymentRepository.save(tx);
        return tx;
    }

    public PaymentTransaction processPayment(String paymentId) {
        Objects.requireNonNull(paymentId);
        PaymentTransaction tx = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found: " + paymentId));

        if (tx.getStatus() == PaymentStatus.SUCCESS || tx.getStatus() == PaymentStatus.FAILED) {
            return tx;
        }

        PaymentGatewayResult result = paymentGateway.charge(tx.getPaymentId(), tx.getAmount());
        tx.setStatus(result.isSuccess() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED, Instant.now());
        paymentRepository.save(tx);
        return tx;
    }
}
