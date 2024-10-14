package org.example.Applicant;

import com.mongodb.client.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.example.EC.EcDTO;
import org.example.Passport.PassportDTO;
import org.example.SGC.SgcDTO;
import org.example.Specialty.SpecialtyDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ApplicantDAO {
    private List<ApplicantDTO> applicants = new ArrayList<>();

    private SpecialtyDTO setSpecialty(String id){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Specialty");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                if(doc.getObjectId("_id").toString().equals(id)) {

                    String newId = doc.getObjectId("_id").toString();
                    int code = doc.getInteger("code");
                    String nameOfSpecialty = doc.getString("name_of_specialty");
                    String nameOfCurriculum = doc.getString("name_of_curiculum");
                    return new SpecialtyDTO(id, code, nameOfSpecialty, nameOfCurriculum);
                }
            }
            return null;
        }
    }

    private EcDTO setEc(String id) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("EC");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                if (doc.getObjectId("_id").toString().equals(id)) {
                    String newId = doc.getObjectId("_id").toString();
                    double avScore = doc.getDouble("av_score");

                    return new EcDTO(newId, avScore);
                }
            }
            return null;
        }
    }

    private PassportDTO setPassport(String id) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Passport");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                if (doc.getObjectId("_id").toString().equals(id)) {
                    String newId = doc.getObjectId("_id").toString();
                    Date dateOfBirth = doc.getDate("date_of_birthday");
                    String placeOfBirthday = doc.getString("place_of_birthday");
                    String sex = doc.getString("sex");

                    return new PassportDTO(newId, dateOfBirth, placeOfBirthday, sex);
                }
            }
            return null;
        }
    }

    private SgcDTO setSgc(String id) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("SGC");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                if (doc.getObjectId("_id").toString().equals(id)) {
                    String newId = doc.getObjectId("_id").toString();
                    double avScore = doc.getDouble("av_score");
                    String honor = doc.getString("honor");

                    return new SgcDTO(newId, avScore, honor);
                }
            }
            return null;
        }
    }

    public void setApplicantFromDB(){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Applicant");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {

                String id = doc.getObjectId("_id").toString();
                String name = doc.getString("name");
                String surname = doc.getString("surname");
                EcDTO ecDTO = setEc(doc.getObjectId("ec_id").toString());
                SgcDTO sgcDTO = setSgc(doc.getObjectId("sgc_id").toString());
                PassportDTO passportDTO = setPassport(doc.getObjectId("passport_id").toString());
                SpecialtyDTO specialtyDTO = setSpecialty(doc.getObjectId("specialty_id").toString());

                ApplicantDTO applicantDTO = new ApplicantDTO(id,name, surname, ecDTO, sgcDTO, passportDTO, specialtyDTO);
                applicants.add(applicantDTO);
            }

        }
    }
}
