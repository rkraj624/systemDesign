package dsa.practice.lld.paymentGateway;

import dsa.practice.lld.paymentGateway.controller.InstrumentController;
import dsa.practice.lld.paymentGateway.controller.TransactionalController;
import dsa.practice.lld.paymentGateway.controller.UserController;
import dsa.practice.lld.paymentGateway.enums.InstrumentType;
import dsa.practice.lld.paymentGateway.model.InstrumentDto;
import dsa.practice.lld.paymentGateway.model.Transaction;
import dsa.practice.lld.paymentGateway.model.TransactionDto;
import dsa.practice.lld.paymentGateway.model.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExecutePayment {
    public static void main(String[] args) {
        System.out.println("LLD Code - Payment Gateway");

        InstrumentController instrumentController = new InstrumentController();
        TransactionalController transactionalController = new TransactionalController();
        UserController userController = new UserController();

        UserDto ravi = new UserDto();
        ravi.setName("Ravi");
        ravi.setEmail("ravi@gmail.com");
        ravi = userController.addUser(ravi);

        UserDto raj = new UserDto();
        raj.setName("Raj");
        raj.setEmail("raj@gmail.com");
        raj = userController.addUser(raj);

        InstrumentDto bank = new InstrumentDto();
        bank.setInstrumentType(InstrumentType.NET_BANKING);
        bank.setBankName("Axis Bank");
        bank.setAccountNumber("1234567890");
        bank.setIfscCode("AXIS0000001");
        bank.setUserId(ravi.getId());

        InstrumentDto raviBank = instrumentController.addInstrument(bank);
        System.out.println("Net Banking added for Ravi " + raviBank.getInstrumentId());

        InstrumentDto creditCard = new InstrumentDto();
        creditCard.setInstrumentType(InstrumentType.CREDIT_CARD);
        creditCard.setCardNumber("1234567890");
        creditCard.setExpiryDate("12/24");
        creditCard.setCvv("123");
        creditCard.setUserId(raj.getId());

        InstrumentDto rajCreditCard = instrumentController.addInstrument(creditCard);
        System.out.println("Credit Card added for Ravi " + rajCreditCard.getInstrumentId());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(1100.0);
        transactionDto.setDebitInstrumentId(raviBank.getInstrumentId());
        transactionDto.setCreditInstrumentId(rajCreditCard.getInstrumentId());
        transactionDto.setSenderId(ravi.getId());
        transactionDto.setReceiverId(raj.getId());

        TransactionDto response = transactionalController.makePayment(transactionDto);
        System.out.println("Transaction Response " + response);

        Set<InstrumentDto> allInstrument = new HashSet<>(instrumentController.getAllInstrument(ravi.getId()));
        System.out.println("All Instrument for Ravi " + allInstrument);
        allInstrument.clear();

        allInstrument = new HashSet<>(instrumentController.getAllInstrument(raj.getId()));
        System.out.println("All Instrument for Raj " + allInstrument);

        List<Transaction> transactionHistory = transactionalController.getTransactionHistory(raj.getId());
        System.out.println("Transaction History for Raj " + transactionHistory);

        transactionHistory.clear();

        transactionHistory = transactionalController.getTransactionHistory(ravi.getId());
        System.out.println("All Instrument for Ravi " + transactionHistory);

    }
}
