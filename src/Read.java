import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;
public class Read {
    public static void readEmployees(Connection connection) {
        System.out.println();
        String selectQuery = "SELECT * FROM employeedata";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Display column headers
            System.out.printf("%-4s %-20s %-4s %-12s %-10s %-15s%n",
                    "ID", "FullName", "Age", "DOB", "Salary", "Department");

            while (resultSet.next()) {
                System.out.printf("%-4d %-20s %-4d %-12s %-10.2f %-15s%n",
                        resultSet.getInt("id"),
                        resultSet.getString("FullName"),
                        resultSet.getInt("Age"),
                        resultSet.getDate("DOB"),
                        resultSet.getDouble("Salary"),
                        resultSet.getString("Department"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

