package fis1;

import java.io.BufferedReader;
import java.io.FileReader;

public class DBConnection {

    public static String url, username, password;

    static {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/db.txt"));
            url = "jdbc:mysql://localhost:3306/fis_proj";
            username = reader.readLine();
            password = reader.readLine();
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
