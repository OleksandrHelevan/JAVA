package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import com.mongodb.client.model.Accumulators;
import org.bson.types.ObjectId;
import org.example.Applicant.ApplicantDTO;
import org.example.EC.EcDTO;
import org.example.Passport.PassportDTO;
import org.example.SGC.SgcDTO;
import org.example.Specialty.SpecialtyDTO;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {

    public void updateManySGC(double changeValue, double setValue) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("SGC");

            collection.updateMany(
                    Filters.lt("av_score", changeValue),
                    Updates.set("av_score", setValue)
            );

        }
    }

    public void deleteOnePassport(String placeOfBirth) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Passport");

            DeleteResult result = collection.deleteOne(new Document("place_of_birthday", placeOfBirth));

            if (result.getDeletedCount() > 0) {
                System.out.println("Document with place of birth '" + placeOfBirth + "' was deleted.");
            } else {
                System.out.println("No document found with this place of birth '" + placeOfBirth + "'.");
            }
        }

    }

    public void setTopItems(Top top) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> applicantCollection = database.getCollection("Applicant");

            applicantCollection.aggregate(Arrays.asList(
                    Aggregates.lookup("EC", "ec_id", "_id", "EC"),
                    Aggregates.unwind("$EC"),
                    Aggregates.sort(Sorts.descending("EC.av_score")),
                    Aggregates.project(Projections.fields(
                            Projections.include("name", "surname"),
                            Projections.computed("av_score", "$EC.av_score")
                    ))
            )).forEach(doc -> {
                String id = doc.getObjectId("_id").toString();
                String name = doc.getString("name");
                String surname = doc.getString("surname");
                double avScore = doc.getDouble("av_score");

                TopItem topItem = new TopItem(id, name, surname, avScore);
                top.addItem(topItem);

            });
        }
    }

    public void getBudgetApplicants() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> applicantCollection = database.getCollection("Applicant");

            applicantCollection.aggregate(Arrays.asList(
                    Aggregates.lookup("Specialty", "specialty_id", "_id", "Specialty"),
                    Aggregates.lookup("EC", "ec_id", "_id", "ec"),
                    Aggregates.unwind("$ec"),
                    Aggregates.match(new Document("ec.av_score", new Document("$gt", 175))),
                    Aggregates.unwind("$Specialty"),
                    Aggregates.project(Projections.fields(
                            Projections.excludeId(),
                            Projections.include("name", "surname"),
                            Projections.computed("av_score", "$ec.av_score"),
                            Projections.computed("name_of_specialty", "$Specialty.name_of_specialty")
                    ))
            )).forEach(doc -> {
                String name = doc.getString("name");
                String surname = doc.getString("surname");
                double avScore = doc.getDouble("av_score");
                String nameOfSpecialty = doc.getString("name_of_specialty");

                System.out.println(name + " " + surname + " " + avScore + " " + nameOfSpecialty);
            });
        }

    }


    public void groupByPlaceOfBirth(){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> passportCollection = database.getCollection("Passport");

            passportCollection.aggregate(Arrays.asList(
                    Aggregates.lookup("Applicant", "_id", "passport_id", "app"),
                    Aggregates.unwind("$app"),
                    Aggregates.group("$place_of_birthday",
                            Accumulators.sum("count", 1)),
                    Aggregates.project(Projections.fields(
                            Projections.excludeId(),
                            Projections.computed("place_of_birthday", "$_id"),
                            Projections.include("count")
                    )),
                    Aggregates.sort(new Document("count", -1))
            )).forEach(doc -> {
                String placeOfBirthday = doc.getString("place_of_birthday");
                int count = doc.getInteger("count");

                System.out.println(placeOfBirthday + ": " + count);
            });
        }
    }

    public void groupSpecialtyByApp(){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> applicantCollection = database.getCollection("Applicant");

            applicantCollection.aggregate(Arrays.asList(
                    Aggregates.group("$specialty_id", Accumulators.sum("count", 1)),
                    Aggregates.lookup("Specialty", "_id", "_id", "spec"),
                    Aggregates.unwind("$spec"),
                    Aggregates.project(Projections.fields(
                            Projections.excludeId(),
                            Projections.include("count"),
                            Projections.computed("name_of_specialty", "$spec.name_of_specialty")
                    )),
                    Aggregates.sort(new Document("count", -1))
            )).forEach(doc -> {
                int count = doc.getInteger("count");
                String nameOfSpecialty = doc.getString("name_of_specialty");

                System.out.println(nameOfSpecialty + ": " + count);
            });
        }
    }

    public void groupFacultyByApp(){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> applicantCollection = database.getCollection("Applicant");

            applicantCollection.aggregate(Arrays.asList(
                    Aggregates.lookup("Specialty", "specialty_id", "_id", "specialty_details"),
                    Aggregates.unwind("$specialty_details"),
                    Aggregates.lookup("Department", "specialty_details.department_id", "_id", "department_details"),
                    Aggregates.unwind("$department_details"),
                    Aggregates.group("$department_details._id",
                            Accumulators.first("name_of_department", "$department_details.name_of_department"),
                            Accumulators.sum("count", 1)
                    ),
                    Aggregates.project(Projections.fields(
                            Projections.excludeId(),
                            Projections.include("name_of_department", "count")
                    )),
                    Aggregates.sort(new Document("count", -1))
            )).forEach(doc -> {
                String nameOfDepartment = doc.getString("name_of_department");
                int count = doc.getInteger("count");

                System.out.println(nameOfDepartment + ": " + count);
            });
        }

    }

    private void addEC(EcDTO ecDTO){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> ecCollection = database.getCollection("EC");
            Document document = new Document("av_score", ecDTO.getAvScore());

            ecCollection.insertOne(document);

            ObjectId objectId = document.getObjectId("_id");
            ecDTO.setId(objectId.toString());

        }
    }

    public void addPassport(PassportDTO passportDTO) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Passport");

            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            Document document = new Document()
                    .append("date_of_birthday", isoFormat.format(passportDTO.getDateOfBirth()))
                    .append("place_of_birthday", passportDTO.getPlaceOfBirthday())
                    .append("sex", passportDTO.getSex());

            collection.insertOne(document);

            ObjectId objectId = document.getObjectId("_id");
            passportDTO.setId(objectId.toString());
        } catch (Exception e) {
            System.out.println("Error while adding passport: " + e.getMessage());
        }
    }

    private void addSGC(SgcDTO sgcDTO) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("SGC");
            Document document = new Document()
                    .append("av_score",sgcDTO.getAvScore())
                    .append("honor", sgcDTO.getHonor());

            collection.insertOne(document);

            ObjectId objectId = document.getObjectId("_id");
            sgcDTO.setId(objectId.toString());
        }
    }


    public SpecialtyDTO findSpecialty(int code){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Specialty");

            Document query = new Document("code", code);
            Document result = collection.find(query).first();

            if (result != null) {
                String id = result.getObjectId("_id").toString();
                int specialtyCode = result.getInteger("code");
                String nameOfSpecialty = result.getString("name_of_specialty");
                String nameOfCurriculum = result.getString("name_of_curiculum");

                return new SpecialtyDTO(id, specialtyCode, nameOfSpecialty, nameOfCurriculum);
            }
        }
        return null;
    }

    public void addApplicant(ApplicantDTO applicantDTO){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

//        try{
//        if(applicantDTO.getSpecialty() == null)
//            throw new RuntimeException("Specialty is null");

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Applicant");

            addEC(applicantDTO.getEc());
            addPassport(applicantDTO.getPassport());
            addSGC(applicantDTO.getSgc());

            Document applicantDocument = new Document("name", applicantDTO.getName())
                    .append("surname", applicantDTO.getSurname())
                    .append("ec_id", new ObjectId(applicantDTO.getEc().getId()))
                    .append("passport_id", new ObjectId(applicantDTO.getPassport().getId()))
                    .append("sgc_id", new ObjectId(applicantDTO.getSgc().getId()))
                    .append("specialty_id", new ObjectId(applicantDTO.getSpecialty().getId()));

            collection.insertOne(applicantDocument);
        }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }

}
