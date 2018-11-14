package java4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private void Deleting(ActionEvent event) {
        int selectedIndex = CatalogTable.getSelectionModel().getSelectedIndex();
//        System.out.println(selectedIndex);
        if (selectedIndex >= 0) {
            CatalogTable.getItems().remove(selectedIndex);
//            CatalogTable.getItems().
        };
    }
}
