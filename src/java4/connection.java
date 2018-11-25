package java4;

import javafx.collections.ObservableList;

import java.sql.*;
import java.lang.String;
import java.util.Properties;

class connection {
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

    static void connect() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*
            Соединение с бд
         */
        Properties p = new Properties();
        p.setProperty("user","root");
        p.setProperty("password","DFRJ752azY@");
        p.setProperty("useUnicode","true");
        p.setProperty("characterEncoding","cp1251");
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection(CONN_STRING, p);
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
        String stats = "INSERT INTO users (firstName, lastName, login, PASS, isAdmin) VALUES (" +
                userName + "," +
                passWord + ",'" +
                currentUser.getLogin() + "','" +
                currentUser.getPass() + "','" +
                "0');";
//        System.out.println(stats);
        stat.execute(stats);
        System.out.println("Пользователь добавлен");
    }

    static void createTable() throws SQLException
    {
        /*
            Создание таблицы пользователей
         */
        stat.execute("CREATE TABLE if not exists users (id INTEGER(10) NOT NULL AUTO_INCREMENT, " +
                "firstName VARCHAR(100)," +
                "lastName VARCHAR(100)," +
                "login VARCHAR(100) not null ," +
                "PASS VARCHAR(100) not null, " +
                "isAdmin Boolean not null," +
                "purchase_id INT (100)," +
                "PRIMARY KEY (id));");
        System.out.println("Таблица создана или уже существует.");
    }

    static ObservableList<product> initDB(ObservableList<product> list) throws SQLException {
        /*
            Инициализация списка товаров
         */
        resSet = stat.executeQuery("select artikul, name, sum, count from goods;") ;
        while (resSet.next()){
            list.add(new product(resSet.getInt("artikul"),
                                resSet.getString("name"),
                                resSet.getDouble("sum"),
                                resSet.getInt("count")));
        }
        return list;
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
        /*
            Проверка существования логина
         */
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

    //добавить класс, который перезаписывает, данные, а ещё создать заново таблицу и тд.
    static void addItem(product curr) throws SQLException {
        /*
        Перезаписывание каждого товара
         */
        curr.setProductName(curr.getProductName().replaceAll("'", ""));
        String a = "INSERT INTO `goods` (`artikul`, `name`, `count`, `sum`) VALUES ('"+
                curr.getProductId() + "', '" +
                curr.getProductName() + "', '"+
                curr.getProductCount() + "', '" +
                curr.getProductSum() + "'); ";
//        System.out.println(a);
        stat.execute(a);

    }

    static void closeDB() throws SQLException, NullPointerException{
        /*
        закрытие бд
         */
        conn.close();
        stat.close();
        System.out.println("Соединения закрыты");
    }

    public static void clearDb() throws SQLException {
        /*
        очистить таблицу
         */
        stat.execute("TRUNCATE TABLE goods;");
    }

    public static boolean isAdmin(String loginOf) throws SQLException {
            String state = "SELECT isAdmin FROM users WHERE login = '" + loginOf + "';";
            resSet = stat.executeQuery(state);
            resSet.next();
            return resSet.getBoolean("isAdmin");
    }

    public static void setTrancToZero() throws SQLException {
        stat.execute("SET FOREIGN_KEY_CHECKS = 0;");
    }

    public static void setTrancToOne() throws SQLException {
        stat.execute("SET FOREIGN_KEY_CHECKS = 1;");
    }

    public static ObservableList<Purchases> getListGoods() throws SQLException {
           /*
        получить(вернуть) список всех покупок из базы данныx в таблице purchase
         */
           ObservableList<Purchases> list = null;
        resSet = stat.executeQuery("select id_purchase, login, date from purchase " +
                "                       left join users on purchase.id_user = users.id;") ;
        while (resSet.next()){
            list.add(new Purchases(resSet.getInt("id_purchase"),
                    resSet.getString("login"),
                    resSet.getDate("date")));
        }
        return list;
    }


//    public static void addOrder(){
//        /*
//        добавить новый заказ
//         */
//
//
//    }
//
//    public static  getList() {

//        return
//    }
}