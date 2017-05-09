package com.healthserviceapp.areas.protocol.serviceImpl;

import com.healthserviceapp.areas.protocol.entities.Protocol;
import com.healthserviceapp.areas.protocol.repositories.ProtocolRepository;
import com.healthserviceapp.areas.protocol.services.ProtocolService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProtocolServiceImpl implements ProtocolService{

    private ProtocolRepository protocolRepository;

    private ModelMapper modelMapper;

    public ProtocolServiceImpl(ProtocolRepository protocolRepository, ModelMapper modelMapper) {
        this.protocolRepository = protocolRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean doesNumberExist(String number) {
        Protocol protocol = this.protocolRepository.findByNumber(number);
        if (protocol != null){
            return true;
        }

        return false;
    }
}
