package java4;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class product {
    String productName;
    Integer productId, productCount;
    Double productSum;

    public product(Integer productId, String productName, Double productSum, Integer productCount) {
        this.productName = productName;
        this.productId = productId;
        this.productCount = productCount;
        this.productSum = productSum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Double getProductSum() {
        return productSum;
    }

    public void setProductSum(Double productSum) {
        this.productSum = productSum;
    }

    @Override
    public String toString() {
        return "product{" +
                "productName='" + productName + '\'' +
                ", productId=" + productId +
                ", productCount=" + productCount +
                ", productSum=" + productSum +
                '}';
    }

    static void rewriteDb(ObservableList<product> list) throws SQLException {
        connection.clearDb();
        list.forEach(product -> {
            try {
                connection.addItem(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
