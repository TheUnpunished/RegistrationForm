package ru.java4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class MainMenuController implements Initializable {

    private final ObservableList<Purchases> orders = FXCollections.observableArrayList();

    @FXML
    private Tab orderTab;

    @FXML
    private Tab catalogTab;

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<Purchases> tableOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("goods.fxml"));
        try {
            catalogTab.setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection.getListGoods(orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        tableOrder.setItems(orders);
    }

    @FXML
    private void setCatalogTab(){
        tabPane.getSelectionModel().select(catalogTab);
    }

    private void setOrderTab(){
        tabPane.getSelectionModel().select(orderTab);
    }


@FXML
    public void clickOpenGoods(javafx.event.ActionEvent event) {
        setCatalogTab();
    }
@FXML
    public void clickShowOrders(ActionEvent event) {
        setOrderTab();
    }
@FXML
    public void openNewWindow(ActionEvent event) throws IOException {
    Stage stage = new Stage();
    stage.setTitle("Товары");
    Parent root = FXMLLoader.load(getClass().getResource("goods.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
@FXML
    public void showInfo(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.NONE);
    alert.setTitle("Информация по");
    alert.setContentText("Программа Учебная.  версия 1.0.3");
    alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
    alert.showAndWait();
    }
}
