package com.example.DBLab11.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
@Scope("prototype")
public class SpecialtyDTO {

    private ObjectId id;
    private Integer code;
    private ObjectId department;
    private String nameOfCurriculum;
    private String nameOfSpecialty;
}
