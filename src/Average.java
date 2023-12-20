import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;
public class Average {
    public static void calculateAverageSalaryByDepartment(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the department to calculate average salary: ");
        String department = scanner.nextLine();

        String query = "SELECT AVG(Salary) AS AverageSalary FROM employeedata WHERE Department = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, department);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double averageSalary = resultSet.getDouble("AverageSalary");
                System.out.println("Average Salary in Department '" + department + "': $" + averageSalary);
            } else {
                System.out.println("No employees found in the specified department.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void calculateOverallAverageSalary(Connection connection) {
        String query = "SELECT AVG(Salary) AS OverallAverageSalary FROM employeedata";
        System.out.println();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                double overallAverageSalary = resultSet.getDouble("OverallAverageSalary");
                System.out.println("Overall Average Salary: $" + overallAverageSalary);
            } else {
                System.out.println("No employees found to calculate overall average salary.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
