package com.iot.iotServer.service;

import com.iot.iotServer.dto.DustDTO;

import java.util.List;

public interface InterfaceDustService {
    DustDTO saveDust(DustDTO dustDTO);
    List<DustDTO> getDustDTOs();
}
