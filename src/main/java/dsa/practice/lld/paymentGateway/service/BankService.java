package dsa.practice.lld.paymentGateway.service;

import dsa.practice.lld.paymentGateway.instrument.BankInstrument;
import dsa.practice.lld.paymentGateway.instrument.Instrument;
import dsa.practice.lld.paymentGateway.model.InstrumentDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankService extends InstrumentService{
    @Override
    public InstrumentDto addInstrument(InstrumentDto instrumentDto) {
        BankInstrument bankInstrument = new BankInstrument();
        bankInstrument.setInstrumentId(UUID.randomUUID().toString());
        bankInstrument.setUserId(instrumentDto.getUserId());
        bankInstrument.setInstrumentType(instrumentDto.getInstrumentType());
        bankInstrument.setBankName(instrumentDto.getBankName());
        bankInstrument.setAccountNumber(instrumentDto.getAccountNumber());
        bankInstrument.setIfscCode(instrumentDto.getIfscCode());
        bankInstrument.setAvailableBalance(1000d);
        userVsInstrumentMap.putIfAbsent(instrumentDto.getUserId(), new ArrayList<>());
        userVsInstrumentMap.get(instrumentDto.getUserId()).add(bankInstrument);
        return mapper.convertValue(bankInstrument, InstrumentDto.class);

    }

    @Override
    public List<InstrumentDto> getInstrumentByUserId(String userId) {
        List<Instrument> instrumentList = userVsInstrumentMap.get(userId);
        List<InstrumentDto> instrumentDtoList = new ArrayList<>();
        for(Instrument instrument : instrumentList){
            instrumentDtoList.add(mapper.convertValue(instrument, InstrumentDto.class));
        }
        return instrumentDtoList;
    }
}
