package dsa.practice.lld.paymentGateway.model;

import dsa.practice.lld.paymentGateway.enums.TransactionStatus;

public class TransactionDto {
    Double amount;
    String debitInstrumentId;
    String creditInstrumentId;
    String senderId;
    String receiverId;
    String transactionId;
    TransactionStatus status;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDebitInstrumentId() {
        return debitInstrumentId;
    }

    public void setDebitInstrumentId(String debitInstrumentId) {
        this.debitInstrumentId = debitInstrumentId;
    }

    public String getCreditInstrumentId() {
        return creditInstrumentId;
    }

    public void setCreditInstrumentId(String creditInstrumentId) {
        this.creditInstrumentId = creditInstrumentId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "amount=" + amount +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", status=" + status +
                '}';
    }
}
