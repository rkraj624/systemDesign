package dsa.practice.lld.paymentGateway.validation;

import dsa.practice.lld.paymentGateway.model.InstrumentDto;
import dsa.practice.lld.paymentGateway.model.TransactionDto;

public class Processor {

    public void processPayment(InstrumentDto senderInstrumentDto, InstrumentDto receiverInstrumentDto, TransactionDto transactionDto){
        double availableBalance = senderInstrumentDto.getAvailableBalance() - transactionDto.getAmount();
        if(availableBalance < 0.0d ){
            throw new RuntimeException("Insufficient Balance for sender " + senderInstrumentDto.getInstrumentId());
        }
        senderInstrumentDto.setAvailableBalance(availableBalance);
        receiverInstrumentDto.setAvailableBalance(receiverInstrumentDto.getAvailableBalance() + transactionDto.getAmount());
    }
}
