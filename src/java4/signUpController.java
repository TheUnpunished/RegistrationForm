package java4;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
            if (FirstName.getText().equals(""))
                current.setFirstName(null);
            if (LastName.getText().equals(""))
                current.setLastName(null);
            connection.createUser(current);
        }

    }

    @FXML
    private void handleButtonAction2(ActionEvent event) {
        /*
        close button
        */

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
