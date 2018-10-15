/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
        String loginOf = TextField1.getText(), passOf = TextField2.getText();
        user newUser = new user(loginOf,passOf);
        boolean accepted = newUser.checkUser(loginOf, passOf);
//        System.out.println(newUser.login);
        if (accepted)
        label.setText(newUser.getLogin() + " вошел");
        else
        label.setText("Неверно");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
