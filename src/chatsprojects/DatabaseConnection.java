/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatsprojects;

/**
 *
 * @author SOMEONE
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL
            = "jdbc:mysql://localhost:3306/aneleproject";

    private static final String USER = "root";

    private static final String PASSWORD = "3770";

    public static Connection getConnection() {

        try {

            // Test if MySQL driver exists
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("MySQL driver found!");

            Connection connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Database connected!");

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL driver NOT found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        return null;
    }
}
