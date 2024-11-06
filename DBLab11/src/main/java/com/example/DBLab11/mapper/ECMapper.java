package com.example.DBLab11.mapper;

import com.example.DBLab11.dto.EcDTO;
import com.example.DBLab11.model.EC;
import org.springframework.stereotype.Component;


@Component
public class ECMapper {

    public EcDTO toDto(EC ec) {
        EcDTO ecDTO = new EcDTO();
        ecDTO.setId(ec.getId());
        ecDTO.setAvScore((int) ec.getAvScore());
        return ecDTO;
    }

    public EC toEntity(EcDTO ecDTO) {
        EC ec = new EC();
        ec.setId(ecDTO.getId());
        ec.setAvScore(ecDTO.getAvScore() != null ? ecDTO.getAvScore() : 0);
        return ec;
    }
}
