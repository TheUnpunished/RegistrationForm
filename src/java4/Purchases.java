package java4;

import javax.xml.crypto.Data;
import java.time.LocalDate;

public class Purchases {
    Integer rowid;
    String login;
    LocalDate date;
    listProduct products;

    void getList(){
        connection.getList();
    }
}
