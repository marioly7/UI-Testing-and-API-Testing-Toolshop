package models;

public class Product {

    private String expectedQuantity;
    private String expectedItem;
    private String expectedPrice;
    private String expectedTotal;
    private String category;

    public Product() {

    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category=category;
    }

    public String getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(String expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public String getExpectedItem() {
        return expectedItem;
    }

    public void setExpectedItem(String expectedItem) {
        this.expectedItem = expectedItem;
    }

    public String getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(String expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public String getExpectedTotal() {
        return expectedTotal;
    }

    public void setExpectedTotal(String expectedTotal) {
        this.expectedTotal = expectedTotal;
    }
}
