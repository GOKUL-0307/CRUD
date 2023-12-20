import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;
public class Delete {
    public static void deleteEmployee(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        // Get user input for the employee name to delete
        System.out.println("Enter the employee FullName:");
        String employeeName = scanner.nextLine();

        // Retrieve the ID of the employee to be deleted
        int deletedEmployeeId = getEmployeeId(connection, employeeName);

        String deleteQuery = "DELETE FROM employeedata WHERE FullName = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, employeeName);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println();
                System.out.println("Employee deleted successfully.");
                System.out.println();

                // Update IDs after deletion
                UpdateID.updateIdsAfterDeletion(connection);
            } else {
                System.out.println("Failed to delete employee. Employee not found.");
            }
        }
    }
    private static int getEmployeeId(Connection connection, String employeeName) throws SQLException {
        String selectQuery = "SELECT id FROM employeedata WHERE FullName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, employeeName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1; // Indicates that the employee was not found
        }
    }
}
