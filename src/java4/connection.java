package java4;

import java.sql.*;
import java.lang.String;

class connection {
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

    private static Connection conn;
    private static Statement stat;
    private static ResultSet resSet = null;

    static void connect() throws ClassNotFoundException, SQLException {
        /*
            Соединение с бд
         */
        conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        System.out.println("connected");
        stat = conn.createStatement();
    }

    static void createUser(user currentUser) throws SQLException, NullPointerException, ClassNotFoundException {
        /*
            Создание пользователя
         */
        String stats = "INSERT INTO users (name, PASS) VALUES ('" +
                currentUser.getLogin() +"','"+
                currentUser.getPass()+ "');";
        stat.execute(stats);
        System.out.println("Пользователь добавлен");
    }

    public static void createTable() throws ClassNotFoundException, SQLException
    {
        /*
            Создание таблицы пользователей
         */
        stat.execute("CREATE TABLE if not exists users (id INTEGER(10) NOT NULL AUTO_INCREMENT, name VARCHAR(100), " +
                "PASS VARCHAR(100), PRIMARY KEY (id));");
        System.out.println("Таблица создана или уже существует.");
    }


    static boolean checkUserByDb(String login, String pass) throws SQLException {
        /*
            Вход пользователя
         */
        String password = null;
        try {
            String state = "SELECT * FROM users WHERE name = '" + login + "';";
            resSet = stat.executeQuery(state);
            resSet.next();
            password = resSet.getString("PASS");
        }
        finally {
            resSet.close();
            return pass.equals(password);
        }


    }


    static void closeDB() throws SQLException, NullPointerException{
        /*
        закрытие бд
         */
        conn.close();
        stat.close();
        System.out.println("Соединения закрыты");
    }
}