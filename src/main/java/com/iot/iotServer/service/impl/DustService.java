package com.iot.iotServer.service.impl;

import com.iot.iotServer.converter.DustConverter;
import com.iot.iotServer.dto.DustDTO;
import com.iot.iotServer.models.Dust;
import com.iot.iotServer.repository.DustRepository;
import com.iot.iotServer.service.InterfaceDustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DustService implements InterfaceDustService {

    @Autowired
    private DustRepository dustRepository;

    @Autowired
    private DustConverter dustConverter;
    @Override
    public DustDTO saveDust(DustDTO dustDTO) {
        Dust dust = dustRepository.save(dustConverter.toEntity(dustDTO));
        return dustConverter.toDTO(dust);
    }

    @Override
    public List<DustDTO> getDustDTOs() {
        List<Dust> dusts = dustRepository.getTenDust();
        List<DustDTO> dustDTOS = new ArrayList<>();
        for(Dust dust : dusts) {
            DustDTO dustDTO = dustConverter.toDTO(dust);
            dustDTOS.add(dustDTO);
        }
        return dustDTOS;
    }
}
