import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;
public class Search {
    public static void searchEmployee(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the employee name to search: ");
        String employeeName = scanner.nextLine();

        String selectQuery = "SELECT * FROM employeedata WHERE FullName = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, employeeName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Employee found, display details
                displayEmployeeDetails(resultSet);
            } else {
                System.out.println("Employee with the name '" + employeeName + "' not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void displayEmployeeDetails(ResultSet resultSet) throws SQLException {
        System.out.println("Employee Details:");
        System.out.println("ID: " + resultSet.getInt("id"));
        System.out.println("FullName: " + resultSet.getString("FullName"));
        System.out.println("Age: " + resultSet.getInt("Age"));
        System.out.println("DOB: " + resultSet.getDate("DOB"));
        System.out.println("Salary: " + resultSet.getDouble("Salary"));
        System.out.println("Department: " + resultSet.getString("Department"));
        System.out.println();
    }
}
