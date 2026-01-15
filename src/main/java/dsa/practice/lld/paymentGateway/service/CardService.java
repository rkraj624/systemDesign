package dsa.practice.lld.paymentGateway.service;

import dsa.practice.lld.paymentGateway.instrument.CardInstrument;
import dsa.practice.lld.paymentGateway.instrument.Instrument;
import dsa.practice.lld.paymentGateway.model.InstrumentDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CardService extends InstrumentService{
    @Override
    public InstrumentDto addInstrument(InstrumentDto instrumentDto) {
        CardInstrument cardInstrument = new CardInstrument();
        int randomNumber = new Random().nextInt(1000);
        cardInstrument.setCardNumber("303089026756"+ randomNumber);
        cardInstrument.setExpiryDate("12/25");
        cardInstrument.setCvv("123");
        cardInstrument.setUserId(instrumentDto.getUserId());
        cardInstrument.setInstrumentType(instrumentDto.getInstrumentType());
        cardInstrument.setInstrumentId(UUID.randomUUID().toString());
        cardInstrument.setAvailableBalance(1000d);
        userVsInstrumentMap.putIfAbsent(instrumentDto.getUserId(), new ArrayList<>());
        userVsInstrumentMap.get(instrumentDto.getUserId()).add(cardInstrument);
        return mapper.convertValue(cardInstrument, InstrumentDto.class);
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
