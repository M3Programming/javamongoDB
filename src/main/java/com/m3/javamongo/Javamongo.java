/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.m3.javamongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.InsertOneResult;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author Marlon
 */
public class Javamongo {

    private static final Scanner scan = new Scanner(System.in);
    private static final String uri = "mongodb://localhost:27017";
    private static final MongoClient mongoClient = MongoClients.create(uri);
    private static final MongoDatabase database = mongoClient.getDatabase("testDB");
    private static final MongoCollection<Document> collection = database.getCollection("userDetails");

    private static void Login() {

        System.out.println("Username: ");
        String username = scan.next();
        System.out.println("Password: ");
        String password = scan.next();

        FindIterable<Document> doc
                = collection.find(and(eq("username", username), eq("password", password)));
        if (doc == null) {
            System.out.println("No results found.");
        } else {
            System.out.println("Login Successful");
            System.out.println("=====================================================================");
            System.out.println();
            System.out.println();
        }

    }

    private static void Register() {
        System.out.println("Firstname: ");
        String firstname = scan.next();
        System.out.println("Lastname: ");
        String lastname = scan.next();
        System.out.println("Username: ");
        String username = scan.next();
        System.out.println("Password: ");
        String password = scan.next();
        Document doc1 = new Document("firstname", firstname).append("lastname", lastname)
                .append("username", username).append("password", password);
        InsertOneResult result = collection.insertOne(doc1);
        System.out.println("Resgistration Complete"
                + result.getInsertedId().asObjectId().getValue());
    }

    public static void main(String[] args) {

        int selection = -1;
        while (selection != 0) {
            System.out.println("Please Select an Action");
            System.out.println("1 -> Login");
            System.out.println("2 -> Register");
            System.out.println("3 -> Exit");
            selection = scan.nextInt();
            switch (selection) {
                case 1 ->
                    Login();
                case 2 ->
                    Register();
                case 3 ->
                    System.exit(0);

                default -> {

                }

            }
        }
    }
}
