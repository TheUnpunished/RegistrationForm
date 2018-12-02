package ru.java4;

import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Purchases {
    Integer id_order;
    String login;
    Date date;
    ObservableList<product> products;

    public Purchases(Integer id_order, String login, Date date) {
        this.id_order = id_order;
        this.login = login;
        this.date = date;
        this.products = null;
    }

    public Purchases(Integer id_order, String login) {
        this.id_order = id_order;
        this.login = login;
        this.date = null;
        this.products = null;
    }

//    static List<Purchases> getList() throws SQLException {
//        return connection.getListGoods(orders);
//    }

    static void findOrder() throws SQLException {
        connection.findOrder("111");
    }
}
