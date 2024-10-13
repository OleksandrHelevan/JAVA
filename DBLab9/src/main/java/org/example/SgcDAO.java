package org.example;
import com.mongodb.client.*;
import lombok.Getter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class SgcDAO {
    private final List<SgcDTO> sgcDTOList = new ArrayList<>();

    public void setSgcFromDB() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);


        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("SGC");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                String id = doc.getObjectId("_id").toString();
                double avScore = doc.getDouble("av_score");
                String honor = doc.getString("honor");

                SgcDTO sgcDTO = new SgcDTO(id, avScore, honor);
                this.sgcDTOList.add(sgcDTO);

            }

        }
    }
}
