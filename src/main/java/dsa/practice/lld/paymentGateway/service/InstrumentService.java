package dsa.practice.lld.paymentGateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dsa.practice.lld.paymentGateway.instrument.Instrument;
import dsa.practice.lld.paymentGateway.model.InstrumentDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class InstrumentService {
    ObjectMapper mapper = new ObjectMapper();

    protected static final Map<String, List<Instrument>> userVsInstrumentMap = new HashMap<>();

    public abstract InstrumentDto addInstrument(InstrumentDto instrumentDto);

    public abstract List<InstrumentDto> getInstrumentByUserId(String instrumentId);

    public void updateInstrument(InstrumentDto senderInstrumentDto){
        List<Instrument> instrumentList = userVsInstrumentMap.get(senderInstrumentDto.getUserId());
        for(Instrument instrument : instrumentList){
            if(instrument.getInstrumentId().equals(senderInstrumentDto.getInstrumentId())){
                instrument.setAvailableBalance(senderInstrumentDto.getAvailableBalance());
            }
        }
    }
}
