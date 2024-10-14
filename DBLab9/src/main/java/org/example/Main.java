package org.example;


import org.example.Passport.PassportDAO;
import org.example.Passport.PassportDTO;
import org.example.SGC.SgcDAO;
import org.example.SGC.SgcDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void line(){
        System.out.println("-------------------------------------------------" +
                "----------------------------------");
    }
    public static void main(String[] args) {
        User user = new User();
        Scanner scanner = new Scanner(System.in);

        String file = "C:\\Users\\Admin\\Desktop\\JAVA\\DBLab9\\src\\main\\java\\org\\example\\Choice.txt";
        int choice = 10;
        while (choice != 0) {
            line();

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String buff;
                while ((buff = br.readLine()) != null) {
                    System.out.println(buff);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            line();
            System.out.println("Make your choice");
            choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1: {
                        SgcDAO sgcDAO = new SgcDAO();
                        sgcDAO.setSgcFromDB();

                        for (SgcDTO sgcDTO : sgcDAO.getSgcDTOList()) {
                            System.out.println(sgcDTO);
                        }
                        line();

                        System.out.println("Enter values which you would like to update");
                        double firstValue = scanner.nextDouble();
                        System.out.println("Enter value for execute updating");
                        double secondValue = scanner.nextDouble();
                        user.updateManySGC(firstValue, secondValue);
                        sgcDAO.clear();
                        sgcDAO.setSgcFromDB();

                        for (SgcDTO sgcDTO : sgcDAO.getSgcDTOList()) {
                            System.out.println(sgcDTO);
                        }
                        break;
                    }
                    case 2: {
                        PassportDAO passportDAO = new PassportDAO();
                        passportDAO.setPassportsFromDB();
                        for (PassportDTO passportDTO : passportDAO.getPassports()) {
                            System.out.println(passportDTO);
                        }
                        line();

                        System.out.println("Enter place of birthday by which you would like to delete passport");
                        String place = scanner.next();
                        user.deleteOnePassport(place);
                        passportDAO.clear();
                        passportDAO.setPassportsFromDB();

                        for (PassportDTO passportDTO : passportDAO.getPassports()) {
                            System.out.println(passportDTO);
                        }
                        break;
                    }
                    case 3: {
                        line();
                        Top top = new Top();
                        user.setTopItems(top);
                        top.printTopItems();
                        break;
                    }
                    case 4: {
                        line();
                        user.getBudgetApplicants();
                    }
                    case 5: {
                        line();
                        user.groupByPlaceOfBirth();
                    }
                    case 6: {
                        line();
                        user.groupSpecialtyByApp();
                    }
                    case 7: {
                        line();
                        user.groupFacultyByApp();
                    }
                    case 0: {
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Invalid choice");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
