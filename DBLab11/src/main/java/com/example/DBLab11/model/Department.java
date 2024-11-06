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
@Document(collection = "Department")
public class Department {

    @Id
    private ObjectId id;

    @DBRef
    @Field(name="faculty_id")
    private ObjectId faculty;

    @Field(name="name_of_department")
    private String nameOfDepartment;

    @Field(name="head_of_department")
    private String headOfDepartment;

}
