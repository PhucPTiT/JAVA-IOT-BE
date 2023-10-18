package com.iot.iotServer.converter;

import com.iot.iotServer.dto.DustDTO;
import com.iot.iotServer.models.Dust;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DustConverter {
    public Dust toEntity(DustDTO dustDTO) {
        Dust dust = new Dust();
        dust.setDust(dustDTO.getDust());
        dust.setTime(LocalDateTime.now());
        return dust;
    }
    public DustDTO toDTO(Dust dust) {
        DustDTO dustDTO = new DustDTO();
        dustDTO.setId(dust.getId());
        dustDTO.setDust(dust.getDust());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeAsString = dust.getTime().format(formatter);
        dustDTO.setTime(timeAsString);

        return dustDTO;
    }
}
