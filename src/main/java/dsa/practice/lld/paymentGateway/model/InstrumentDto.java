package dsa.practice.lld.paymentGateway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import dsa.practice.lld.paymentGateway.enums.InstrumentType;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstrumentDto {
    String instrumentId;
    String userId;
    InstrumentType instrumentType;
    String bankName;
    String accountNumber;
    String ifscCode;
    String cardNumber;
    String expiryDate;
    String cvv;
    Double availableBalance;

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public String toString() {
        return "InstrumentDto{" +
                "instrumentId='" + instrumentId + '\'' +
                ", userId='" + userId + '\'' +
                ", instrumentType=" + instrumentType +
                ", availableBalance=" + availableBalance +
                '}';
    }
}
