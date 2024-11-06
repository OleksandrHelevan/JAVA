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
public class EcDTO {

    private ObjectId id;
    private Integer avScore;
}
