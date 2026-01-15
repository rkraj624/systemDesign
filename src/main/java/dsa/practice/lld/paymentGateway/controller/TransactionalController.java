package dsa.practice.lld.paymentGateway.controller;

import dsa.practice.lld.paymentGateway.model.Transaction;
import dsa.practice.lld.paymentGateway.model.TransactionDto;
import dsa.practice.lld.paymentGateway.service.TransactionService;

import java.util.List;

public class TransactionalController {
    TransactionService transactionService;

    public TransactionalController() {
        this.transactionService = new TransactionService();
    }

    public TransactionDto makePayment(TransactionDto transactionDto){
        return transactionService.makePayment(transactionDto);
    }

    public List<Transaction> getTransactionHistory(String userId){
        return transactionService.getTransactionHistory(userId);
    }
}
