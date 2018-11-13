package java4;

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
}
