package java4;

import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Purchases {
    Integer id_order;
    String login;
    Date date;

    public Purchases(Integer id_order, String login, Date date) {
        this.id_order = id_order;
        this.login = login;
        this.date = date;
    }

    ObservableList<product> products;

    ObservableList<Purchases> getList() throws SQLException {
        ObservableList<Purchases> n = connection.getListGoods();
        return n;
    }
}
