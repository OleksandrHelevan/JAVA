package org.example.Department;

import com.mongodb.client.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.example.Specialty.SpecialtyDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDAO {
    List<DepartmentDTO> departments = new ArrayList<>();

    public static void setSpecialty(DepartmentDTO department) {
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

                if(department.getId().equals(doc.getObjectId("department_id").toString())) {

                    SpecialtyDTO specialtyDTO = new SpecialtyDTO(id, code, nameOfSpecialty, nameOfCurriculum);
                    department.addSpecialty(specialtyDTO);
                }


            }
        }
    }

    public void setDepartmentsFromDB(){
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

                DepartmentDTO departmentDTO = new DepartmentDTO(id, name, head, new ArrayList<>());
                setSpecialty(departmentDTO);
                this.departments.add(departmentDTO);

            }

        }
    }
}
