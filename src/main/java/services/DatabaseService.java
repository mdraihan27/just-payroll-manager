package services;
import java.sql.*;

public class DatabaseService {

    final static String URL = "jdbc:postgresql://localhost:5432/just_payroll_manager";
    final static String USER = "raihan";
    final static String PASSWORD = "raihan";

    public boolean isDatabaseOnline(){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Connected to PostgreSQL successfully.");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            e.printStackTrace();
            return false;
        }
    }

    public static void executeUpdate(String update) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(update);
            System.out.println("Query executed. Rows affected: " + rowsAffected);

        }
        catch (SQLException e) {

            if(e.getMessage().toString().equals("ERROR: relation \"employees\" already exists")){
                System.out.println("Employee table already exists.");
            }
            else{
                System.out.println("Database update failed.");
                e.printStackTrace();
            }
        }
    }

    public static ResultSet executeQuery(String query) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            return rs;



        } catch (SQLException e) {
            System.out.println("Database query failed.");
            e.printStackTrace();
            return null;
        }
    }



}
