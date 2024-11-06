package com.example.DBLab11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Passport")
public class Passport {
    @Id
    private ObjectId id;

    @NonNull
    @Field(name = "date_of_birthday")
    private Date dateOfBirthday;

    @Field(name = "place_of_birthday")
    private String placeOfBirthday;

    @Field(name = "sex_birthday")
    private String sex;


}
