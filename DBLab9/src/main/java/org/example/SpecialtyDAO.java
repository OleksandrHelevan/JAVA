package org.example;

import com.mongodb.client.*;
import lombok.Getter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class SpecialtyDAO {
    private final List<SpecialtyDTO> specialties = new ArrayList<>();


    public void setSpecialtiesFromDB(){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Specialty");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {

                String id = doc.getObjectId("_id").toString();
                int code = doc.getInteger("code");
                String nameOfSpecialty = doc.getString("name_of_specialty");
                String nameOfCurriculum = doc.getString("name_of_curiculum");

                SpecialtyDTO specialtyDTO = new SpecialtyDTO(id, code, nameOfSpecialty, nameOfCurriculum);
                specialties.add(specialtyDTO);
            }
        }
    }
}
