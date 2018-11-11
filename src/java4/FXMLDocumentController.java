/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java4;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author rbyhf
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField TextField1;
    @FXML
    private TextField TextField2;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
//        System.out.println("You clicked me!");

        String loginOf = TextField1.getText(), passOf = TextField2.getText();
        user newUser = new user(loginOf, passOf);
//        boolean accepted = newUser.checkUser(loginOf, passOf); // проверка через текстовый файл
//        System.out.println(newUser.login);
        boolean accepted = connection.checkUserByDb(loginOf, passOf);
        if (accepted) {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Вход");
            alert.setContentText("Пользователь " + newUser.getLogin() + " успешно вошел");
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.showAndWait();
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

    public void handleButtonAction2(ActionEvent event) throws SQLException, ClassNotFoundException {
        /*
        создать пользователя
         */
        String loginOf = TextField1.getText(), passOf = TextField2.getText();
        user newUser = new user(loginOf, passOf);
        connection.createUser(newUser);
    }

    public void cancelAction(ActionEvent event) {
        // TODO
    }
}
