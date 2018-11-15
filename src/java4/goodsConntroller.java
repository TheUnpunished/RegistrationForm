package java4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

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
        /*
        Заполняем каталог товарами
         */
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
        /*
        заполнить поля текущим продуктом
         */
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
        /*
           При нажатии кнопки удалить выбранный элемент извлекается,
           создается новый список товаров,
           который перезаписывается в базу данных
         */
        int selectedIndex = CatalogTable.getSelectionModel().getSelectedIndex();
//        System.out.println(selectedIndex);
        if (selectedIndex >= 0) {
            CatalogTable.getItems().remove(selectedIndex);
//            CatalogTable.getItems().forEach(product -> System.out.println(product.toString()));
            product.rewriteDb(CatalogTable.getItems());
        }
    }

    @FXML
    public void adding(ActionEvent event) throws IOException {
        /*
        Добавление товара в базу данных при нажатии кнопки
         */
    product temp = new product();

    boolean okClicked = this.showProductEditDialog(temp);
    if (okClicked){
        Data.add(temp);
    }
    }

    public boolean showProductEditDialog(product current){
        /*
            При нажатии кнопки добавить или изменить запускается данное диалоговое окно
         */
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(addController.class.getResource("addingAction.fxml"));
            AnchorPane page = (AnchorPane) loader.load();


            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактировать продукт");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            addController controller = loader.getController();
            controller.setStageDialog(dialogStage);
            controller.setProduct(current);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void actionEdit(ActionEvent event) {
        product selected = CatalogTable.getSelectionModel().getSelectedItem();
        if (selected != null){
            boolean okClicked = showProductEditDialog(selected);
            if (okClicked){
                showProductDetails(selected);
                int selectedIndex = CatalogTable.getSelectionModel().getSelectedIndex();
                Data.set(selectedIndex, selected);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(null);
                alert.setTitle("Ничего не выбрано");
                alert.setHeaderText("Нет выбранного продукта");
                alert.setContentText("Выберите продукт");

                alert.showAndWait();
            }
        }
    }
}
