package com.example.DBLab11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Specialty")
public class Specialty {

    @Id
    private ObjectId id;

    @Field(name="code")
    private int code;

    @DBRef
    @Field(name="department_id")
    private ObjectId department;

    @Field(name="name_of_curiculum")
    private String nameOfCurriculum;

    @Field(name="name_of_specialty")
    private String nameOfSpecialty;


}
