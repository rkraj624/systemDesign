package dsa.practice.lld.paymentGateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dsa.practice.lld.paymentGateway.controller.InstrumentController;
import dsa.practice.lld.paymentGateway.enums.TransactionStatus;
import dsa.practice.lld.paymentGateway.model.InstrumentDto;
import dsa.practice.lld.paymentGateway.model.Transaction;
import dsa.practice.lld.paymentGateway.model.TransactionDto;
import dsa.practice.lld.paymentGateway.validation.Processor;

import java.util.*;

public class TransactionService {

    public static Map<String, List<Transaction>> userVsTransactionMap = new HashMap<>();

    ObjectMapper objectMapper = new ObjectMapper();

    InstrumentController instrumentController;
    Processor processor;

    public TransactionService() {
        this.instrumentController = new InstrumentController();
        this.processor = new Processor();
    }


    public TransactionDto makePayment(TransactionDto transactionDto) {
        // validate details

        // load sender instrument details which need to be passed to processors
        InstrumentDto senderInstrumentDto = instrumentController.getInstrumentById(transactionDto.getSenderId(), transactionDto.getDebitInstrumentId());

        // load receiver instrument details which need to be passed to processors
        InstrumentDto receiverInstrumentDto = instrumentController.getInstrumentById(transactionDto.getReceiverId(), transactionDto.getCreditInstrumentId());

        // pass the instrument details to processor
        processor.processPayment(senderInstrumentDto, receiverInstrumentDto, transactionDto);

        updateTransaction(senderInstrumentDto, receiverInstrumentDto);

        //based on processor response, we will set the status. for now har coding it to SUCCESS
        Transaction txn = new Transaction();
        txn.setAmount(transactionDto.getAmount());
        txn.setTransactionId(UUID.randomUUID().toString());
        txn.setSenderId(transactionDto.getSenderId());
        txn.setReceiverId(transactionDto.getReceiverId());
        txn.setDebitInstrumentId(transactionDto.getDebitInstrumentId());
        txn.setCreditInstrumentId(transactionDto.getCreditInstrumentId());
        txn.setStatus(TransactionStatus.SUCCESS);

        //history

        List<Transaction> senderTxnsList = userVsTransactionMap.computeIfAbsent(txn.getSenderId(), k -> new ArrayList<>());
        senderTxnsList.add(txn);

        List<Transaction> receiverTxnLists = userVsTransactionMap.computeIfAbsent(txn.getReceiverId(), k -> new ArrayList<>());
        receiverTxnLists.add(txn);

        return objectMapper.convertValue(txn, TransactionDto.class);

    }

    private void updateTransaction(InstrumentDto senderInstrumentDto, InstrumentDto receiverInstrumentDto) {
        instrumentController.updateInstrument(senderInstrumentDto);
        instrumentController.updateInstrument(receiverInstrumentDto);
    }

    public List<Transaction> getTransactionHistory(String userId) {
        return userVsTransactionMap.get(userId);
    }
}
