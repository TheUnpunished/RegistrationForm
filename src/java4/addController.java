package java4;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class addController implements Initializable {


//    public javafx.scene.control.TextField idFeld;
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField countField;
    @FXML
    private TextField sumField;




    private Stage stageDialog;
    private product productCurrent;
    private boolean isOkClicked = false;


    @FXML
    private void cancelAction(){
        stageDialog.close();
    }
    @FXML
    private void actionOK(ActionEvent event) throws SQLException {
        /*
        при нажатии кнопки ок создаем новый объект продукта,
        берем данные из заполненных ранее полей.
         */
        if (isInputValid()){
            productCurrent = new product(Integer.parseInt(idField.getText()),
                                         nameField.getText(),
                                         Double.parseDouble(countField.getText()),
                                         Integer.parseInt(sumField.getText()));
            connection.addItem(productCurrent);
            isOkClicked = true;
            stageDialog.close();
        }


    }

    public void setProduct(product productCurrent) {
        /*
        установить текущие значения продукта в полях сцены
         */
        this.productCurrent = productCurrent;
        nameField.setText(productCurrent.getProductId().toString());
        idField.setText(String.valueOf(productCurrent.getProductName()));
        sumField.setText(String.valueOf(productCurrent.getProductSum()));
        countField.setText(String.valueOf(productCurrent.getProductCount()));
    }

    public void setStageDialog(Stage stageDialog) {
        /*
        установить сцену для этого окна
         */
        this.stageDialog = stageDialog;
    }

    public boolean isOkClicked() {
        /*
            проверка на нажатие кнопки ОК
         */
        return isOkClicked;
    }

    private boolean isInputValid(){
        /*
        Проверка на правильность ввода данных
         */
        String errorMessage = "";

        if (idField.getText().length() == 0 || idField.getText() == null) errorMessage += "Нет доступного артикула \n";

        if (nameField.getText().length() == 0 || nameField.getText() == null) errorMessage += "Нет доступного наименования товара \n";

        if (sumField.getText().length() == 0 || sumField.getText() == null) errorMessage += "Нет доступной суммы\n";

        if (countField.getText().length() == 0 || countField.getText() == null) errorMessage += "Нет доступного количества\n";

        if (errorMessage.length() == 0 ){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stageDialog);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внести корректную информацию");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }


}
