import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;
public class Create {
    public static void createEmployee(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        // Get user input for new employee
        System.out.println("Enter Full Name:");
        String fullName = scanner.nextLine();

        System.out.println("Enter Age:");
        int age = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character left by nextInt()

        System.out.println("Enter Date of Birth (YYYY-MM-DD):");
        String dob = scanner.nextLine();

        System.out.println("Enter Salary:");
        double salary = scanner.nextDouble();

        scanner.nextLine(); // Consume the newline character left by nextDouble()

        System.out.println("Enter Department:");
        String department = scanner.nextLine();

        // SQL query for inserting new employee
        String insertQuery = "INSERT INTO employeedata (FullName, Age, DOB, Salary, Department) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // Set values based on user input
            preparedStatement.setString(1, fullName);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, dob);
            preparedStatement.setDouble(4, salary);
            preparedStatement.setString(5, department);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the employee was created successfully
            if (rowsAffected > 0) {
                System.out.println();
                System.out.println("Employee created successfully.");
                System.out.println();
            } else {
                System.out.println("Failed to create employee.");
            }
        }
    }
}
