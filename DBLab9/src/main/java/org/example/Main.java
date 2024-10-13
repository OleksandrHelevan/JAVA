package org.example;

public class Main {
    public static void main(String[] args) {

//        PassportDAO passportDAO = new PassportDAO();
//        passportDAO.setPassportsFromDB();
//        for(PassportDTO passportDTO: passportDAO.getPassports()){
//            System.out.println(passportDTO);
//        }
//        EcDAO ecDAO = new EcDAO();
//        ecDAO.setEcFromDB();
//        for(EcDTO ecDTO: ecDAO.getEcDTOList()){
//            System.out.println(ecDTO);
//        }
//
//        SgcDAO sgcDAO = new SgcDAO();
//        sgcDAO.setSgcFromDB();
//        for(SgcDTO sgcDTO: sgcDAO.getSgcDTOList()){
//            System.out.println(sgcDTO);
//        }

        FacultyDAO facultyDAO = new FacultyDAO();
        facultyDAO.setFacultyFromDB();
        for (FacultyDTO facultyDTO: facultyDAO.getFacultyList()) {
            System.out.println(facultyDTO);
            for(DepartmentDTO departmentDTO: facultyDTO.getDepartments()){
                System.out.println("\t" + departmentDTO);
                for(SpecialtyDTO specialtyDTO: departmentDTO.getSpecialties()){
                    System.out.println("\t\t" + specialtyDTO);
                }
            }
        }
    }
}
