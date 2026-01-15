package dsa.practice.lld.paymentGateway.controller;

import dsa.practice.lld.paymentGateway.enums.InstrumentType;
import dsa.practice.lld.paymentGateway.factory.InstrumentServiceFactory;
import dsa.practice.lld.paymentGateway.model.InstrumentDto;
import dsa.practice.lld.paymentGateway.service.InstrumentService;

import java.util.List;

public class InstrumentController {

    InstrumentServiceFactory instrumentServiceFactory;

    public InstrumentController() {
        this.instrumentServiceFactory = new InstrumentServiceFactory();
    }

    public InstrumentDto addInstrument(InstrumentDto instrumentDto){
        InstrumentService instrumentService = instrumentServiceFactory.getInstrumentService(instrumentDto.getInstrumentType());
        return instrumentService.addInstrument(instrumentDto);
    }

    public List<InstrumentDto> getAllInstrument(String userId){
        InstrumentService cardService = instrumentServiceFactory.getInstrumentService(InstrumentType.CREDIT_CARD);
        InstrumentService bankService = instrumentServiceFactory.getInstrumentService(InstrumentType.NET_BANKING);
        List<InstrumentDto> instrumentList = cardService.getInstrumentByUserId(userId);
        instrumentList.addAll(bankService.getInstrumentByUserId(userId));
        return instrumentList;
    }

    public InstrumentDto getInstrumentById(String userId, String instrumentId){
        List<InstrumentDto> instrumentDtoList = getAllInstrument(userId);
        for(InstrumentDto instrumentDto : instrumentDtoList){
            if(instrumentDto.getInstrumentId().equals(instrumentId)){
                return instrumentDto;
            }
        }
        return null;
    }

    public void updateInstrument(InstrumentDto senderInstrumentDto) {
        InstrumentService instrumentService = instrumentServiceFactory.getInstrumentService(senderInstrumentDto.getInstrumentType());
        instrumentService.updateInstrument(senderInstrumentDto);
    }
}
