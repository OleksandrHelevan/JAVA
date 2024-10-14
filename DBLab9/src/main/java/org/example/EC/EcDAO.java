package org.example.EC;

import com.mongodb.client.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EcDAO {
    private List<EcDTO> ecDTOList = new ArrayList<>();

    public void setEcFromDB() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);


        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("EC");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                String id = doc.getObjectId("_id").toString();
                double avScore = doc.getDouble("av_score");

                EcDTO ecDTO = new EcDTO(id, avScore);
                this.ecDTOList.add(ecDTO);

            }

        }
    }

}
