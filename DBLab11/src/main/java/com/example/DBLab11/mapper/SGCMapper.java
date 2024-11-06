package com.example.DBLab11.mapper;

import com.example.DBLab11.dto.SgcDTO;
import com.example.DBLab11.model.SGC;
import org.springframework.stereotype.Component;

@Component
public class SGCMapper {

    public SgcDTO toDto(SGC sgc) {
        SgcDTO sgcDTO = new SgcDTO();
        sgcDTO.setId(sgc.getId());
        sgcDTO.setAvScore(sgc.getAvScore());
        sgcDTO.setHonor(sgc.getHonor());
        return sgcDTO;
    }

    public SGC toEntity(SgcDTO sgcDTO) {
        SGC sgc = new SGC();
        sgc.setId(sgcDTO.getId());
        sgc.setAvScore(sgcDTO.getAvScore());
        sgc.setHonor(sgcDTO.getHonor());
        return sgc;
    }
}
