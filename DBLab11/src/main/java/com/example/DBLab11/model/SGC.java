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
@Document(collection = "SGC")
public class SGC {

    @Id
    private ObjectId id;

    @Field(name="av_score")
    private double avScore;

    @Field(name="honor")
    private String honor;


}
