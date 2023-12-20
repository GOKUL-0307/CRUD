// Import statements
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;

public class Main {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost/crud";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "gokul";

    public static void main(String[] args) {
        Connection connection = null;

        // Display welcome message
        displayWelcomeMessage();

        try {
            // Establishing a connection to the database
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Scanner scanner = new Scanner(System.in);
            String userChoice;

            do {
                // Display menu options
                System.out.println("Choose an operation:");
                System.out.println("1. Search Employee");
                System.out.println("2. Display Employees Data");
                System.out.println("3. Create/Insert Employee");
                System.out.println("4. Update Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. Filter Employees");
                System.out.println("7. Calculate Average Salary by Department");
                System.out.println("8. Calculate Overall Average Salary");
                System.out.println("9. End");

                System.out.print("Enter your choice: ");
                userChoice = scanner.nextLine();

                switch (userChoice) {
                    case "1":
                        System.out.println(" ".repeat(40) +"SEARCHING EMPLOYEE DATA");
                        Search.searchEmployee(connection);
                        System.out.println();
                        break;
                    case "2":
                        System.out.println(" ".repeat(40) +"DISPLAYING EMPLOYEE DATA");
                        Read.readEmployees(connection);
                        System.out.println();
                        break;
                    case "3":
                        System.out.println(" ".repeat(40)+"INSERTING EMPLOYEE DATA");
                        Create.createEmployee(connection);
                        UpdateID.updateIdsAfterDeletion(connection);
                        Read.readEmployees(connection);
                        break;
                    case "4":
                        System.out.println(" ".repeat(40) +"UPDATING EMPLOYEE DATA");
                        Update.updateEmployee(connection);
                        UpdateID.updateIdsAfterDeletion(connection);
                        Read.readEmployees(connection);
                        break;
                    case "5":
                        System.out.println(" ".repeat(40) +"DELETING EMPLOYEE DATA");
                        Delete.deleteEmployee(connection);
                        UpdateID.updateIdsAfterDeletion(connection);
                        Read.readEmployees(connection);
                        break;
                    case "6":
                        System.out.println(" ".repeat(40) +"FILTERING EMPLOYEE DATA");
                        Filter.filterEmployees(connection);
                        System.out.println();
                        break;
                    case "7":
                        System.out.println(" ".repeat(40) +"AVERAGE SALARY BY DEPARTMENT");
                        Average.calculateAverageSalaryByDepartment(connection);
                        break;
                    case "8":
                        System.out.println(" ".repeat(40) +"OVERALL SALARY");
                        Average.calculateOverallAverageSalary(connection);
                        break;
                    case "9":
                        System.out.println("Thank you for using the CRUD Project!");
                        System.out.println("Have a great day and see you soon!😇");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
                System.out.println("---------🍀🍀🍀---------🍀🍀🍀---------🍀🍀🍀---------🍀🍀🍀---------🍀🍀🍀---------🍀🍀🍀---------🍀🍀🍀---------🍀🍀🍀---------   ");

            } while (!userChoice.equals("9"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the connection in the finally block
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Display a welcome message to the user.
     */
    private static void displayWelcomeMessage() {
        int consoleWidth = 80;  // Adjust this based on your console width

        // Calculate the number of spaces to center the text
        int spacesBefore = 20 + (consoleWidth - 40) / 2;

        // Display the welcome message with leading spaces
        System.out.println(" ".repeat(spacesBefore) + "********************************************");
        System.out.println(" ".repeat(spacesBefore) + "* 🎇🎇Welcome to the CRUD Project🎇🎇 *");
        System.out.println(" ".repeat(spacesBefore) + "********************************************");
        System.out.println(" ".repeat(spacesBefore) + "*  This is a simple CRUD (Create, Read,    *");
        System.out.println(" ".repeat(spacesBefore) + "*  Update, Delete) project in Java.        *");
        System.out.println(" ".repeat(spacesBefore) + "*  Manage employee data with ease!         *");
        System.out.println(" ".repeat(spacesBefore) + "********************************************");
        System.out.println();
    }

}
