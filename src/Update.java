import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;
public class Update {
    public static void updateEmployee(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        // Display options for updating
        System.out.println("Choose the attribute to update:");
        System.out.println("1. Age");
        System.out.println("2. DOB");
        System.out.println("3. Salary");
        System.out.println("4. Department");

        // Get user choice
        int choice = scanner.nextInt();

        // Consume the newline character left by nextInt()
        scanner.nextLine();

        // SQL query for updating employee
        String updateQuery = null;

        switch (choice) {
            case 1:
                updateQuery = "UPDATE employeedata SET Age = ? WHERE FullName = ?";
                break;
            case 2:
                updateQuery = "UPDATE employeedata SET DOB = ? WHERE FullName = ?";
                break;
            case 3:
                updateQuery = "UPDATE employeedata SET Salary = ? WHERE FullName = ?";
                break;
            case 4:
                updateQuery = "UPDATE employeedata SET Department = ? WHERE FullName = ?";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        // Get user input for the employee name
        System.out.println("Enter the employee name:");
        String employeeName = scanner.nextLine();

        // Get user input for the updated value
        String updatedValue;

        if (choice == 2) {
            // If updating DOB, mention the format
            System.out.println("Enter the updated value (YYYY-MM-DD):");
            updatedValue = scanner.nextLine();
        } else {
            // For other attributes, directly get the user input
            System.out.println("Enter the updated value:");
            updatedValue = scanner.nextLine();
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, updatedValue);
            preparedStatement.setString(2, employeeName);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the employee was updated successfully
            if (rowsAffected > 0) {
                System.out.println();
                System.out.println("Employee updated successfully.");
                System.out.println();
            } else {
                System.out.println("Failed to update employee.");
            }
        }
    }
}
