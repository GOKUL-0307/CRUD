import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;

public class Filter {
    public  static void filterEmployees(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a filter criteria:");
        System.out.println("1. Based on Age");
        System.out.println("2. Based on Salary");
        System.out.println("3. Based on Department");
        System.out.println("4. Based on Salary and Age");
        System.out.println("5. Based on Salary and Department");
        System.out.println("6. Based on Age and Department");
        System.out.println("7. Based on Salary, Age, and Department");

        System.out.print("Enter your choice: ");
        int filterChoice = scanner.nextInt();

        switch (filterChoice) {
            case 1:
                filterByAge(connection);
                break;
            case 2:
                filterBySalary(connection);
                break;
            case 3:
                filterByDepartment(connection);
                break;
            case 4:
                filterBySalaryAndAge(connection);
                break;
            case 5:
                filterBySalaryAndDepartment(connection);
                break;
            case 6:
                filterByAgeAndDepartment(connection);
                break;
            case 7:
                filterBySalaryAgeAndDepartment(connection);
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }




    private static void filterByAge(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose age filter:");
        System.out.println("1. Age >= ");
        System.out.println("2. Age <= ");
        System.out.print("Enter your choice: ");
        int ageFilterChoice = scanner.nextInt();

        System.out.print("Enter the age value: ");
        int ageValue = scanner.nextInt();

        String filterQuery = "";

        switch (ageFilterChoice) {
            case 1:
                filterQuery = "SELECT * FROM employeedata WHERE Age >= ?";
                break;
            case 2:
                filterQuery = "SELECT * FROM employeedata WHERE Age <= ?";
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                return;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(filterQuery)) {
            preparedStatement.setInt(1, ageValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            displayFilteredResults(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void filterBySalary(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose salary filter:");
        System.out.println("1. Salary >= ");
        System.out.println("2. Salary <= ");
        System.out.print("Enter your choice: ");
        int salaryFilterChoice = scanner.nextInt();

        System.out.print("Enter the salary value: ");
        double salaryValue = scanner.nextDouble();

        String filterQuery = "";

        switch (salaryFilterChoice) {
            case 1:
                filterQuery = "SELECT * FROM employeedata WHERE Salary >= ?";
                break;
            case 2:
                filterQuery = "SELECT * FROM employeedata WHERE Salary <= ?";
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                return;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(filterQuery)) {
            preparedStatement.setDouble(1, salaryValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            displayFilteredResults(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void filterByDepartment(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the department name: ");
        String departmentName = scanner.nextLine();

        String filterQuery = "SELECT * FROM employeedata WHERE Department = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(filterQuery)) {
            preparedStatement.setString(1, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();

            displayFilteredResults(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void filterBySalaryAndAge(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose salary filter:");
        System.out.println("1. Salary >= ");
        System.out.println("2. Salary <= ");
        System.out.print("Enter your choice: ");
        int salaryFilterChoice = scanner.nextInt();

        System.out.print("Enter the salary value: ");
        double salaryValue = scanner.nextDouble();

        System.out.println("Choose age filter:");
        System.out.println("1. Age >= ");
        System.out.println("2. Age <= ");
        System.out.print("Enter your choice: ");
        int ageFilterChoice = scanner.nextInt();

        System.out.print("Enter the age value: ");
        int ageValue = scanner.nextInt();

        String filterQuery = "";

        switch (salaryFilterChoice) {
            case 1:
                filterQuery += "Salary >= ?";
                break;
            case 2:
                filterQuery += "Salary <= ?";
                break;
            default:
                System.out.println("Invalid choice for salary filter. Please enter a valid option.");
                return;
        }

        switch (ageFilterChoice) {
            case 1:
                filterQuery += (filterQuery.isEmpty() ? "" : " AND ") + "Age >= ?";
                break;
            case 2:
                filterQuery += (filterQuery.isEmpty() ? "" : " AND ") + "Age <= ?";
                break;
            default:
                System.out.println("Invalid choice for age filter. Please enter a valid option.");
                return;
        }

        filterQuery = "SELECT * FROM employeedata WHERE " + filterQuery;

        try (PreparedStatement preparedStatement = connection.prepareStatement(filterQuery)) {
            preparedStatement.setDouble(1, salaryValue);
            preparedStatement.setInt(2, ageValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            displayFilteredResults(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void filterBySalaryAndDepartment(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose salary filter:");
        System.out.println("1. Salary >= ");
        System.out.println("2. Salary <= ");
        System.out.print("Enter your choice: ");
        int salaryFilterChoice = scanner.nextInt();

        System.out.print("Enter the salary value: ");
        double salaryValue = scanner.nextDouble();

        System.out.print("Enter the department name: ");
        String departmentName = scanner.next();

        String filterQuery = "";

        switch (salaryFilterChoice) {
            case 1:
                filterQuery = "Salary >= ?";
                break;
            case 2:
                filterQuery = "Salary <= ?";
                break;
            default:
                System.out.println("Invalid choice for salary filter. Please enter a valid option.");
                return;
        }

        filterQuery = "SELECT * FROM employeedata WHERE " + filterQuery + " AND Department = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(filterQuery)) {
            preparedStatement.setDouble(1, salaryValue);
            preparedStatement.setString(2, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();

            displayFilteredResults(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void filterByAgeAndDepartment(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose age filter:");
        System.out.println("1. Age >= ");
        System.out.println("2. Age <= ");
        System.out.print("Enter your choice: ");
        int ageFilterChoice = scanner.nextInt();

        System.out.print("Enter the age value: ");
        int ageValue = scanner.nextInt();

        System.out.print("Enter the department name: ");
        String departmentName = scanner.next();

        String filterQuery = "";

        switch (ageFilterChoice) {
            case 1:
                filterQuery = "Age >= ?";
                break;
            case 2:
                filterQuery = "Age <= ?";
                break;
            default:
                System.out.println("Invalid choice for age filter. Please enter a valid option.");
                return;
        }

        filterQuery = "SELECT * FROM employeedata WHERE " + filterQuery + " AND Department = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(filterQuery)) {
            preparedStatement.setInt(1, ageValue);
            preparedStatement.setString(2, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();

            displayFilteredResults(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void filterBySalaryAgeAndDepartment(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose salary filter:");
        System.out.println("1. Salary >= ");
        System.out.println("2. Salary <= ");
        System.out.print("Enter your choice: ");
        int salaryFilterChoice = scanner.nextInt();

        System.out.print("Enter the salary value: ");
        double salaryValue = scanner.nextDouble();

        System.out.println("Choose age filter:");
        System.out.println("1. Age >= ");
        System.out.println("2. Age <= ");
        System.out.print("Enter your choice: ");
        int ageFilterChoice = scanner.nextInt();

        System.out.print("Enter the age value: ");
        int ageValue = scanner.nextInt();

        System.out.print("Enter the department name: ");
        String departmentName = scanner.next();

        String filterQuery = "";

        switch (salaryFilterChoice) {
            case 1:
                filterQuery += "Salary >= ?";
                break;
            case 2:
                filterQuery += "Salary <= ?";
                break;
            default:
                System.out.println("Invalid choice for salary filter. Please enter a valid option.");
                return;
        }

        switch (ageFilterChoice) {
            case 1:
                filterQuery += (filterQuery.isEmpty() ? "" : " AND ") + "Age >= ?";
                break;
            case 2:
                filterQuery += (filterQuery.isEmpty() ? "" : " AND ") + "Age <= ?";
                break;
            default:
                System.out.println("Invalid choice for age filter. Please enter a valid option.");
                return;
        }

        filterQuery = "SELECT * FROM employeedata WHERE " + filterQuery + " AND Department = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(filterQuery)) {
            preparedStatement.setDouble(1, salaryValue);
            preparedStatement.setInt(2, ageValue);
            preparedStatement.setString(3, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();

            displayFilteredResults(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void displayFilteredResults(ResultSet resultSet) throws SQLException {
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
    }
}
