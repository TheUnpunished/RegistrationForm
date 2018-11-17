package java4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private void actionOK() throws IOException {
        /*
        при нажатии кнопки ок создаем новый объект продукта,
        берем данные из заполненных ранее полей.
         */
        if (isInputValid()){
//            System.out.println("here");
            productCurrent.setProductId(Integer.parseInt(idField.getText()));
            productCurrent.setProductName(String.valueOf(nameField.getText()));
            productCurrent.setProductCount(Integer.parseInt(countField.getText()));
            productCurrent.setProductSum(Double.parseDouble(sumField.getText()));
            isOkClicked = true;
            stageDialog.close();
        }


    }

    void setProduct(product productCurrent) {
        /*
        установить текущие значения продукта в полях сцены
         */
        this.productCurrent = productCurrent;
//        System.out.println("sas");
//        System.out.println("a\n" + productCurrent.getProductId().toString());
        idField.setText(productCurrent.getProductId() == null ? "" :productCurrent.getProductId().toString());
        nameField.setText(productCurrent.getProductName());
        countField.setText(productCurrent.getProductCount() == null ? "" : productCurrent.getProductCount().toString());
        sumField.setText(productCurrent.getProductSum() == null ? "" : productCurrent.getProductSum().toString());
    }

    void setStageDialog(Stage stageDialog) {
        /*
        установить сцену для этого окна
         */
        this.stageDialog = stageDialog;
    }

    boolean isOkClicked() {
        /*
            проверка на нажатие кнопки ОК
         */
        return isOkClicked;
    }

    private boolean isInputValid() throws NullPointerException  {
        /*
        Проверка на правильность ввода данных
         */
        String errorMessage = "";
//        System.out.println(idField.getText().equals(""));
//        System.out.println(nameField.getText().length());
//        System.out.println(countField.getText().length());
//        System.out.println(sumField.getText().length());
        if (idField.getText().length() == 0 || !idField.getText().matches("[-+]?\\d+")) errorMessage += "Нет доступного артикула \n";

        if (nameField.getText() == null)
            errorMessage += "Нет доступного наименования товара \n";

        if (countField.getText().length() == 0  || !countField.getText().matches("[-+]?\\d+")) errorMessage += "Нет доступного количества\n";

        try {
            Double.parseDouble(sumField.getText());
            if (sumField.getText().length() == 0) errorMessage += "Нет доступной суммы\n";
        } catch (NumberFormatException e){
            errorMessage += "Нет доступной суммы\n";
        }

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
//        setStageDialog(stageDialog);
    }
}