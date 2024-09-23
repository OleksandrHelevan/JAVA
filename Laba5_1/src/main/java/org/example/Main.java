package org.example;
import org.example.admin.*;
import org.example.client.ClientDAO;


public class Main {

    public static void main(String[] args) throws Exception {
        Authorizer authorizer = new Authorizer();
        AdminDAO admin = authorizer.adminAuthorization
                ("Oleksandr","12345678");

        ClientDAO client = authorizer.clientAuthorization
                ("Oleksandr", "12345678");

    }
}