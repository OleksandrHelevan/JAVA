package com.example.DBLab11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Faculty")
public class Faculty {

    @Id
    private ObjectId id;

    @Field(name="contact")
    private String contact;

    @Field(name="dean_name")
    private String deanName;

    @Field(name="name_of_faculty")
    private String nameOfFaculty;

    @Field(name="address")
    private String address;

    @Field(name="dean_surname")
    private String deanSurname;

}
