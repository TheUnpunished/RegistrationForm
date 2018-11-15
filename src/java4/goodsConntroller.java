package java4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class goodsConntroller implements Initializable {

    private final ObservableList<product> Data = FXCollections.observableArrayList();

    @FXML
    private TableView<product> CatalogTable;
    @FXML
    private TableColumn<product, String> productId;
    @FXML
    private TableColumn<product, String>  productName;
    @FXML
    private TableColumn<product, String> productSum;
    @FXML
    private TableColumn<product, String> productCount;
    @FXML
    private Label productIdLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productSumLabel;
    @FXML
    private Label productCountLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection.initDB(Data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Data.add(new product(1, "chair", 1500.0, 2));
//        Data.add(new product(2,"cnjk", 123.0, 4));
    productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
    productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
    productSum.setCellValueFactory(new PropertyValueFactory<>("productSum"));
    productCount.setCellValueFactory(new PropertyValueFactory<>("productCount"));
    CatalogTable.setItems(Data);


    showProductDetails(null);

        CatalogTable.getSelectionModel().selectedItemProperty().
                addListener(((observable, oldValue, newValue)
                        -> showProductDetails(newValue)));
    }

    private void showProductDetails(product current){
        if (current != null){
            productIdLabel.setText(current.getProductId().toString());
            productNameLabel.setText(String.valueOf(current.getProductName()));
            productSumLabel.setText(String.valueOf(current.getProductSum()));
            productCountLabel.setText(String.valueOf(current.getProductCount()));
        } else {
            productIdLabel.setText("");
            productNameLabel.setText("");
            productSumLabel.setText("");
            productCountLabel.setText("");
        }
    }
    @FXML
    private void Deleting(ActionEvent event) throws SQLException {
        int selectedIndex = CatalogTable.getSelectionModel().getSelectedIndex();
//        System.out.println(selectedIndex);
        if (selectedIndex >= 0) {
            CatalogTable.getItems().remove(selectedIndex);
//            CatalogTable.getItems().forEach(product -> System.out.println(product.toString()));
            product.rewriteDb(CatalogTable.getItems());
        }
    }

    public void adding(ActionEvent event) throws IOException {
//        stage1.close();
        Stage stage = new Stage();
        stage.setTitle("Добавить товар");
        Parent root = FXMLLoader.load(getClass().getResource("addingAction.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
