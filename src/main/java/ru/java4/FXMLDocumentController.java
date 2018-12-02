/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.java4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 *
 * @author rbyhf
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField TextField1;
    @FXML
    private TextField TextField2;
    @FXML
    private Hyperlink HyperLink1;
    @FXML
    private Button buttonAction1;
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
//        System.out.println("You clicked me!");

        String loginOf = TextField1.getText(), passOf = TextField2.getText();
//        user newUser = new user(loginOf, passOf);
//        boolean accepted = newUser.checkUser(loginOf, passOf); // проверка через текстовый файл
//        System.out.println(newUser.login);
        boolean accepted = connection.checkUserByDb(loginOf, passOf), admin = connection.isAdmin(loginOf);
        if (accepted) {
            if (admin) {
                Stage stage1 = (Stage) buttonAction1.getScene().getWindow();
                stage1.close();
                Stage stage = new Stage();
                stage.setTitle("Товары");
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/MainMenu.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Stage stage1 = (Stage) buttonAction1.getScene().getWindow();
                stage1.close();
                Stage stage = new Stage();
                stage.setTitle("Товары");
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/goods.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Сообщение об ошибке");
            alert.setContentText("Проверьте введенные логин и пароль");
            alert.showAndWait();
        }
//        */
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleButtonAction2(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        /*
        создать пользователя
         */
        Stage stage1 = (Stage) HyperLink1.getScene().getWindow();
        stage1.close();
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/signUp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void cancelAction(ActionEvent event) {
        TextField1.clear();
        TextField2.clear();
    }
}
