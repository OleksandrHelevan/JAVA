package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Choose what do you want");
        Scanner scanner = new Scanner(System.in);
        HashRecorder recorder = new HashRecorder();
        int choice = 10;
        while (choice != 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\Desktop\\JAVA\\Lab10DB\\src\\main\\resources\\options.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            choice = new Scanner(System.in).nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter the code of Specialty:");
                    String code = scanner.nextLine();
                    System.out.println("Enter the name of Specialty:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the curriculum of Specialty:");
                    String curriculum = scanner.nextLine();
                    System.out.println("Enter the Department id of Specialty:");
                    String department = scanner.nextLine();
                    recorder.createSpecialty(code, name, curriculum, department);
                    break;
                }
                case 2: {
                    System.out.println("Enter the id of Passport:");
                    String id = scanner.nextLine();
                    System.out.println("Enter date of birth (YYYY-MM-DD):");
                    String dateOfBirth = scanner.nextLine();
                    System.out.println("Enter place of birth:");
                    String placeOfBirth = scanner.nextLine();
                    System.out.println("Enter sex (Male/Female):");
                    String sex = scanner.nextLine();
                    recorder.createPassport(id, dateOfBirth, placeOfBirth, sex);
                    break;
                }
                case 3: {
                    System.out.println("Enter the id of SGC:");
                    String id = scanner.nextLine();
                    System.out.println("Enter average score:");
                    String avScore = scanner.nextLine();
                    System.out.println("Enter honor:");
                    String honor = scanner.nextLine();
                    recorder.createSGC(id, avScore, honor);
                    break;
                }
                case 4: {
                    System.out.println("Enter name of Applicant:");
                    String name = scanner.nextLine();
                    System.out.println("Enter surname of Applicant:");
                    String surname = scanner.nextLine();
                    System.out.println("Enter Passport id:");
                    String passportId = scanner.nextLine();
                    System.out.println("Enter EC id:");
                    String ecId = scanner.nextLine();
                    System.out.println("Enter SGC id:");
                    String sgcId = scanner.nextLine();
                    System.out.println("Enter Specialty code:");
                    String specialtyCode = scanner.nextLine();
                    recorder.createApplicant(name, surname, passportId, ecId, sgcId, specialtyCode);
                    break;
                }
                case 5: {
                    System.out.println("Enter the key to read:");
                    String key = scanner.nextLine();
                    System.out.println(recorder.read(key));
                    break;
                }
                case 6: {
                    System.out.println("Enter the code of Specialty to update:");
                    String code = scanner.nextLine();
                    System.out.println("Enter the new name of Specialty:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the new curriculum of Specialty:");
                    String curriculum = scanner.nextLine();
                    System.out.println("Enter the new Department id of Specialty:");
                    String department = scanner.nextLine();
                    recorder.updateSpecialty(code, name, curriculum, department);
                    break;
                }
                case 7: {
                    System.out.println("Enter the id of Passport to update:");
                    String id = scanner.nextLine();
                    System.out.println("Enter new date of birth (YYYY-MM-DD):");
                    String dateOfBirth = scanner.nextLine();
                    System.out.println("Enter new place of birth:");
                    String placeOfBirth = scanner.nextLine();
                    System.out.println("Enter new sex (Male/Female):");
                    String sex = scanner.nextLine();
                    recorder.updatePassport(id, dateOfBirth, placeOfBirth, sex);
                    break;
                }
                case 8: {
                    System.out.println("Enter the key to delete:");
                    String key = scanner.nextLine();
                    recorder.delete(key);
                    System.out.println("Deleted key: " + key);
                    break;
                }
                case 0: {
                    System.out.println("Exiting...");
                    break;
                }
                default: {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

        }
    }

}