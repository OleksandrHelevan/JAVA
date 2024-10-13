package org.example;

import com.mongodb.client.*;
import lombok.Getter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class FacultyDAO {
    private final List<FacultyDTO> facultyList = new ArrayList<>();

    private void setDepartments(FacultyDTO faculty) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);


        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Department");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                String id = doc.getObjectId("_id").toString();
                String name = doc.getString("name_of_department");
                String head = doc.getString("head_of_department");
                String faculty_id = doc.getObjectId("faculty_id").toString();

                if(faculty.getId().equals(faculty_id)) {
                 faculty.addDepartment(new DepartmentDTO(id, name, head,new ArrayList<>()));
                }

            }

        }
    }

    public void setFacultyFromDB(){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Faculty");

            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                String id = doc.getObjectId("_id").toString();
                String facultyName = doc.getString("name_of_faculty");
                String address = doc.getString("address");
                String contact = doc.getString("contact");
                String deanName = doc.getString("dean_name");
                String deanSurname = doc.getString("dean_surname");

                FacultyDTO faculty = new FacultyDTO(id, facultyName,contact,address,deanName,deanSurname, new ArrayList<>());
                this.setDepartments(faculty);

                for(DepartmentDTO departmentDTO : faculty.getDepartments()) {
                    DepartmentDAO.setSpecialty(departmentDTO);
                }
                this.facultyList.add(faculty);
            }

        }
    }

}
