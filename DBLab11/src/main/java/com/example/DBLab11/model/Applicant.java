package com.example.DBLab11.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "Applicant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {

    @Id
    private String id;

    @DBRef
    @Field(name="ec_id")
    private String ec;

    @Field(name="name")
    private String name;

    @DBRef
    @Field(name="passport_id")
    private String passport;

    @DBRef
    @Field(name="sgc_id")
    private String sgc;

    @DBRef
    @Field(name="specialty_id")
    private String specialty;

    @Field(name="surname")
    private String surname;

}
