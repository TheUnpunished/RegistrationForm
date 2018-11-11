package java4;

import java.sql.*;
import java.lang.String;

public class connection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "DFRJ752azY@";
    private static final String CONN_STRING =
            "jdbc:mysql://localhost:3306/Users"+
                    "?verifyServerCertificate=false"+
                    "&useSSL=false"+
                    "&requireSSL=false"+
                    "&useLegacyDatetimeCode=false"+
                    "&amp"+
                    "&serverTimezone=UTC";

    public static Connection conn;
    public static Statement stat;
    public static ResultSet resSet;

    public static void connect() throws ClassNotFoundException, SQLException {
        conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        System.out.println("connected");
    }

    static boolean checkUserByDb(String login, String pass) throws SQLException {
        stat = conn.createStatement();
        String state = "SELECT * FROM simple WHERE name = '" + login + "'";
        resSet = stat.executeQuery(state);
        String  password = resSet.getString("PASSWORD");
        return pass == password;
    }



    //    static void addUser() throws SQLException {
//        stat.executeQuery("")
//    }

    static void closeDB() throws SQLException {
        conn.close();
        System.out.println("Соединения закрыты");
    }


}
