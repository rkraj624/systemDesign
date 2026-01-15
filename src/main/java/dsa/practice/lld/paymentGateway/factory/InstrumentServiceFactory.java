package dsa.practice.lld.paymentGateway.factory;

import dsa.practice.lld.paymentGateway.enums.InstrumentType;
import dsa.practice.lld.paymentGateway.service.BankService;
import dsa.practice.lld.paymentGateway.service.CardService;
import dsa.practice.lld.paymentGateway.service.InstrumentService;

public class InstrumentServiceFactory {
    public InstrumentService getInstrumentService(InstrumentType instrumentType){

        if(instrumentType == InstrumentType.NET_BANKING){
            return new BankService();
        }else if(instrumentType == InstrumentType.CREDIT_CARD){
            return new CardService();
        }else if(instrumentType == InstrumentType.DEBIT_CARD){
            return new CardService();
        }
        return null;
    }
}
