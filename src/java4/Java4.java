/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rbyhf
 */
public class Java4 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        System.out.println("Start");
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Вход");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        connection.connect();
        connection.createTable();
//        Purchases.getList().forEach(n -> System.out.println(n.id_order + " " + n.login + " " + n.date));
        connection.findOrder("1243");
        launch(args);
        connection.closeDB();
    }
    
}
