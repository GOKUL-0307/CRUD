import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;
public class UpdateID {
    public static void updateIdsAfterDeletion(Connection connection) throws SQLException {
        // Reset the auto-increment counter to 1
        String resetAutoIncrementQuery = "ALTER TABLE employeedata AUTO_INCREMENT = 1;";

        // Set the variable @new_id to 0
        String setVariableQuery = "SET @new_id = 0;";

        // Update the entire id column sequentially
        String updateIdsQuery = "UPDATE employeedata SET id = (@new_id := @new_id + 1);";

        try (Statement statement = connection.createStatement()) {
            // Reset auto-increment counter
            statement.executeUpdate(resetAutoIncrementQuery);

            // Set variable
            statement.executeUpdate(setVariableQuery);

            // Update entire id column
            statement.executeUpdate(updateIdsQuery);
        }
    }
}
