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

    static void connect() throws SQLException {
        /*
            Соединение с бд
         */
        conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        System.out.println("connected");
        stat = conn.createStatement();
        
    }

    static void createUser(user currentUser) throws SQLException, NullPointerException {
        /*
            Создание пользователя
         */
        String userName = currentUser.getFirstName().equals("") ? "null"
                : "'" + currentUser.getFirstName() + "'",
                passWord = currentUser.getLastName().equals("") ? "null"
                        : "'" + currentUser.getLastName() + "'";
        String stats = "INSERT INTO users (firstName, lastName, login, PASS) VALUES (" +
                userName + "," +
                passWord + ",'" +
                currentUser.getLogin() + "','" +
                currentUser.getPass() + "');";
//        System.out.println(stats);
        stat.execute(stats);
        System.out.println("Пользователь добавлен");
    }

    static void createTable() throws ClassNotFoundException, SQLException
    {
        /*
            Создание таблицы пользователей
         */
        stat.execute("CREATE TABLE if not exists users (id INTEGER(10) NOT NULL AUTO_INCREMENT, " +
                "firstName VARCHAR(100)," +
                "lastName VARCHAR(100)," +
                "login VARCHAR(100) not null ," +
                "PASS VARCHAR(100) not null, " +
                "PRIMARY KEY (id));");
        System.out.println("Таблица создана или уже существует.");
    }


    static boolean checkUserByDb(String login, String pass) throws SQLException {
        /*
            Вход пользователя
         */
        String password = null;
        try {
            String state = "SELECT PASS FROM users WHERE login = '" + login + "';";
            resSet = stat.executeQuery(state);
            resSet.next();
            password = resSet.getString("PASS");
        }
        finally {
            resSet.close();
            return pass.equals(password);
        }
    }

    static boolean exist(String name){
        try {
            String state = "SELECT PASS FROM users WHERE login = '" + name + "';";
            resSet = stat.executeQuery(state);
            resSet.next();
            resSet.getString("PASS");
            return true;
        } catch (SQLException e){
            return false;
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