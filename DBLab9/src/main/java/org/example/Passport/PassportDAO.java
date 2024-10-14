package org.example.Passport;


import com.mongodb.client.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassportDAO {
    private List<PassportDTO> passports = new ArrayList<>();

    public void clear(){
       passports.clear();
    }
    public void setPassportsFromDB() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);


        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Passport");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                String id = doc.getObjectId("_id").toString();
                Date dateOfBirth = doc.getDate("date_of_birthday");
                String placeOfBirthday = doc.getString("place_of_birthday");
                String sex = doc.getString("sex");

                PassportDTO passportDTO = new PassportDTO(id, dateOfBirth, placeOfBirthday, sex);
                this.passports.add(passportDTO);

            }

        }
    }
}
