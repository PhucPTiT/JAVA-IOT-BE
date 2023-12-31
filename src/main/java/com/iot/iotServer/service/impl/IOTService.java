package com.iot.iotServer.service.impl;

import com.iot.iotServer.converter.ControlLogConverter;
import com.iot.iotServer.converter.DataSensorConverter;
import com.iot.iotServer.dto.ControlLogDTO;
import com.iot.iotServer.dto.DataSensorDTO;
import com.iot.iotServer.models.ControlLog;
import com.iot.iotServer.models.DataSensor;
import com.iot.iotServer.repository.ControlLogRepository;
import com.iot.iotServer.repository.DataSensorRepository;
import com.iot.iotServer.service.InterfaceIOTService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IOTService implements InterfaceIOTService {
    @Autowired
    private ControlLogRepository controlLogRepository;

    @Autowired
    private ControlLogConverter controlLogConverter;

    @Autowired
    private DataSensorConverter dataSensorConverter;

    @Autowired
    private DataSensorRepository dataSensorRepository;
    @Override
    public ControlLogDTO saveControl(ControlLogDTO controlLogDTO) {
        ControlLog controlLogEntity = new ControlLog();
        controlLogEntity = controlLogRepository.save(controlLogConverter.toEntity(controlLogDTO));
        return controlLogConverter.toDTO(controlLogEntity);
    }

    @Override
    public ControlLogDTO updateControl(ControlLogDTO controlLogDTO) {
        Optional<ControlLog> optiControlLogEX = controlLogRepository.findById(controlLogDTO.getId());
        if(optiControlLogEX.isPresent()) {
            ControlLog controlLogEX = optiControlLogEX.get();
            return controlLogConverter.toDTO(controlLogRepository.save(controlLogConverter.toEntity(controlLogDTO, controlLogEX)));
        }
        return null;
    }

    @Override
    public List<ControlLogDTO> getAllControlLog() {
        List<ControlLog> controlLogs = controlLogRepository.findAll();
        List<ControlLogDTO> controlLogDTOs = new ArrayList<>();
        for (int i = controlLogs.size() - 1; i >= 0; i--) {
            ControlLog controlLog = controlLogs.get(i);
            ControlLogDTO controlLogDTO = controlLogConverter.toDTO(controlLog);
            controlLogDTOs.add(controlLogDTO);
        }
        return controlLogDTOs;
    }

    @Override
    public ControlLogDTO getFanControlLogFirst() {
        ControlLog controlLog = controlLogRepository.findLastControlLogByDevice("fan");
        if (controlLog == null) {
            return null;
        }
        return controlLogConverter.toDTO(controlLog);
    }

    @Override
    public ControlLogDTO getLightControlLogFirst() {
        ControlLog controlLog = controlLogRepository.findLastControlLogByDevice("light");
        if (controlLog == null) {
            return null;
        }
        return controlLogConverter.toDTO(controlLog);
    }

    @Override
    public DataSensorDTO getFirstDataSensor() {
        DataSensor dataSensor = dataSensorRepository.findLastDataSensor();
        if(dataSensor != null) {
            return dataSensorConverter.toDTO(dataSensor);
        }
        else {
            DataSensorDTO defaultDataSensorDTO = new DataSensorDTO();
            defaultDataSensorDTO.setId(null);
            defaultDataSensorDTO.setTemp("0");
            defaultDataSensorDTO.setHumidity("0");
            defaultDataSensorDTO.setBrightness("0");
            defaultDataSensorDTO.setTime(null);
            return defaultDataSensorDTO;
        }
    }

    @Override
    public Page<DataSensor> getDataPana(int page, int size, String sd, String ed, String key) {
        Sort sort = Sort.by(Sort.Direction.DESC, "time");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DataSensor> dataSensorPage;
        if("".equals(sd) && "".equals(ed)) {
            if("".equals(key)) {
                dataSensorPage = dataSensorRepository.findAll(pageable);
            } else {
                dataSensorPage = dataSensorRepository.filterData(null,null,key, pageable);
            }

        } else {
            if ("".equals(key)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
                dataSensorPage = dataSensorRepository.filterData(LocalDateTime.parse(sd, formatter), LocalDateTime.parse(ed, formatter), "", pageable);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
                dataSensorPage = dataSensorRepository.filterData(LocalDateTime.parse(sd, formatter), LocalDateTime.parse(ed, formatter), key, pageable);
            }
        }
        return dataSensorPage;
    }

    @Override
    public void deleteByControlId(Long id) {
        controlLogRepository.deleteById(id);
    }

    @Override
    public void deleteByDataId(Long id) {
        dataSensorRepository.deleteById(id);
    }

    @Override
    public DataSensorDTO updateData(DataSensorDTO dataSensorDTO) {
        Optional<DataSensor> optiDataSensonEX = dataSensorRepository.findById(dataSensorDTO.getId());
        if(optiDataSensonEX.isPresent()) {
            DataSensor dataSensor = optiDataSensonEX.get();
            return dataSensorConverter.toDTO(dataSensorRepository.save(dataSensorConverter.toEntity(dataSensorDTO, dataSensor)));
        }
        return null;
    }

}

