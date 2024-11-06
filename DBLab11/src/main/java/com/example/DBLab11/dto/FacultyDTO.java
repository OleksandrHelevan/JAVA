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
public class FacultyDTO {

    private ObjectId id;
    private String contact;
    private String deanName;
    private String nameOfFaculty;
    private String address;
    private String deanSurname;
}
