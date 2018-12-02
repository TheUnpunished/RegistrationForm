package ru.java4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author rbyhf
 */
public class signUpController implements Initializable {

    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField repeatPassword;
    @FXML
    private TextField Login;
    @FXML
    private Button closeButton;
    @FXML
    private Button signUpButtonSignUp;
    @FXML
    private Hyperlink signInLink;

    @FXML
    private void handleButtonAction1(ActionEvent event) throws SQLException {
    /*
    register user
    */
//        System.out.println(Login.getText());
    boolean isExist = connection.exist(Login.getText()),
            checkPass = Password.getText().equals(repeatPassword.getText());
//        System.out.println(checkPass);
        if (Login.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Сообщение об ошибке");
            alert.setContentText("Введите логин");
            alert.showAndWait();
        } else if (!checkPass){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Сообщение об ошибке");
            alert.setContentText("Введенные пароли не совпадают");
            alert.showAndWait();
        } else if (isExist){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Сообщение об ошибке");
            alert.setContentText("Пользователь с таким логином уже существует");
            alert.showAndWait();
        } else if (Password.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Сообщение об ошибке");
            alert.setContentText("Введите пароль");
            alert.showAndWait();
        } else {

            user current = new user(FirstName.getText(),
                                    LastName.getText(),
                                    Login.getText(),
                                    Password.getText());
            connection.createUser(current);
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Регистрация");
            alert.setContentText("Пользователь зарегистрирован");
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    private void handleButtonAction2(ActionEvent event) {
        /*
        close button
        */
        Stage stage1 = (Stage) closeButton.getScene().getWindow();
        stage1.close();
    }
    @FXML
    private void hyperText2(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) signInLink.getScene().getWindow();
        stage1.close();
        Stage stage = new Stage();
        stage.setTitle("Вход");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
