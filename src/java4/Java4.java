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
    @Override
    public void start(Stage stage) throws Exception {
//        System.out.println("Start");
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connected");



        } catch (SQLException e) {
            System.err.println(e);
        }

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Вход");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
