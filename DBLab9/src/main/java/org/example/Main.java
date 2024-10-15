package org.example;

import lombok.Data;
import org.example.Applicant.ApplicantDTO;
import org.example.EC.EcDTO;
import org.example.Passport.PassportDAO;
import org.example.Passport.PassportDTO;
import org.example.SGC.SgcDAO;
import org.example.SGC.SgcDTO;
import org.example.Specialty.SpecialtyDAO;
import org.example.Specialty.SpecialtyDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void line() {
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
                    case 8: {
                        line();
                        ApplicantDTO applicant = new ApplicantDTO();

                        System.out.println("Enter your name:");
                        String name = scanner.next();
                        applicant.setName(name);
                        line();

                        System.out.println("Enter your surname:");
                        String surname = scanner.next();
                        applicant.setSurname(surname);
                        line();

                        PassportDTO passport = new PassportDTO();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dateFormat.setLenient(false);
                        Date dateOfBirth = null;

                        System.out.println("Enter date of birthday (format: yyyy-MM-dd): ");
                        while (dateOfBirth == null) {
                            String inputDate = scanner.nextLine();
                            if (inputDate.trim().isEmpty()) {
                                continue;
                            }
                            try {
                                dateOfBirth = dateFormat.parse(inputDate);
                            } catch (ParseException e) {
                                System.out.println("Wrong type of date. Use the format yyyy-MM-dd.");
                                System.out.println("Enter date of birthday (format: yyyy-MM-dd): ");

                            }
                        }
                        passport.setDateOfBirth(dateOfBirth);

                        String sex;
                        while (true) {
                            System.out.println("Enter your sex (M/F):");
                            sex = scanner.next().toUpperCase();

                            if (sex.equals("M") || sex.equals("F")) {
                                break;
                            } else {
                                System.out.println("Invalid input. Enter 'M' for Male or 'F' for Female.");
                            }
                        }
                        passport.setSex(sex);
                        line();

                        System.out.println("Enter your place of birthday:");
                        String place = new Scanner(System.in).next();
                        passport.setPlaceOfBirthday(place);
                        line();

                        applicant.setPassport(passport);

                        EcDTO ec = new EcDTO();
                        System.out.println("Enter your average score of exam certificate:");
                        double scoreEc = new Scanner(System.in).nextDouble();
                        ec.setAvScore(scoreEc);
                        line();

                        applicant.setEc(ec);

                        SgcDTO sgc = new SgcDTO();
                        System.out.println("Enter yor average score of school graduating certificate: ");
                        double scoreSgc = new Scanner(System.in).nextDouble();
                        sgc.setAvScore(scoreSgc);
                        line();

                        System.out.println("Enter name of some honors froms school (if you have)");
                        String honor = new Scanner(System.in).next();
                        sgc.setHonor(honor);
                        line();

                        applicant.setSgc(sgc);

                        SpecialtyDAO specialtyDAO = new SpecialtyDAO();
                        specialtyDAO.setSpecialtiesFromDB();
                        for (SpecialtyDTO specialtyDTO : specialtyDAO.getSpecialties()) {
                            System.out.println(specialtyDTO);
                        }
                        line();

                        System.out.println("Enter code of specialty to which you would like to enter");
                        int specialtyCode = scanner.nextInt();
                        SpecialtyDTO specialtyDTO = user.findSpecialty(specialtyCode);
                        applicant.setSpecialty(specialtyDTO);

                        user.addApplicant(applicant);

                        break;

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
