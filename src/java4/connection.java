package java4;

import java.sql.*;
import java.lang.String;

public class connection {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static final String USERNAME = "skitel";
    private static final String PASSWORD = "DFRJ752azY!";
    private static final String CONN_STRING =
            "jdbc:mysql://localhost:3306/my"+
                    "?verifyServerCertificate=false"+
                    "&useSSL=false"+
                    "&requireSSL=false"+
                    "&useLegacyDatetimeCode=false"+
                    "&amp"+
                    "&serverTimezone=UTC";
    static void connectToDB() {
        // try connect to db

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

            System.out.println("Connected");
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    static void performQuery(String query) throws SQLException {
        connectToDB();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            System.out.printf("id: %d name: %s", id, name);
//        }

        }
    }
}
