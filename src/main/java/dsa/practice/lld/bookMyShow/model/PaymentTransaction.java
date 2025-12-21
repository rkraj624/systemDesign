package dsa.practice.lld.bookMyShow.model;

import dsa.practice.lld.bookMyShow.enums.PaymentStatus;

import java.time.Instant;
import java.util.Objects;

public class PaymentTransaction {
    private final String paymentId;
    private final String bookingId;
    private final int amount;
    private final Instant createdAt;

    private PaymentStatus status;
    private Instant updatedAt;

    public PaymentTransaction(String paymentId, String bookingId, int amount, Instant createdAt) {
        this.paymentId = Objects.requireNonNull(paymentId);
        this.bookingId = Objects.requireNonNull(bookingId);
        this.amount = amount;
        this.createdAt = Objects.requireNonNull(createdAt);
        this.status = PaymentStatus.INITIATED;
        this.updatedAt = createdAt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public int getAmount() {
        return amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setStatus(PaymentStatus status, Instant updatedAt) {
        this.status = Objects.requireNonNull(status);
        this.updatedAt = Objects.requireNonNull(updatedAt);
    }
}
