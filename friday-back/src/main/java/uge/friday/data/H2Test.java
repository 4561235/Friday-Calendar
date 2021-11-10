package uge.friday.data;

//import org.h2.tools.Server;
import org.apache.maven.settings.Server;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class H2Test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:./h2Data/testdb", "test", "123");
            System.out.println("Connection Established: " + conn.getMetaData().getDatabaseProductName() + "/" + conn.getCatalog());

            var query = "SELECT * FROM event, id_calendarType, id_recurrence where id_recurrence = id_recurrence.id and id_calendarType = id_calendarType.id;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    System.out.printf("%d %s %s %s %s\n", rs.getInt(1), rs.getString(2),rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(12));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
