package java4;

import java.net.URL;
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
    private Hyperlink signInLink;
    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
