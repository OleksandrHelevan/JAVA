package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import com.mongodb.client.model.Accumulators;

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

            UpdateResult result = collection.updateMany(
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

}
