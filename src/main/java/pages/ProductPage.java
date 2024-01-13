package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

public class ProductPage extends BasePage {

    protected final By increaseQuantityButton = By.id("btn-increase-quantity");
    protected final By addToCartButton = By.id("btn-add-to-cart");
    protected final By successMessage = By.xpath("//div[@class='toast-body' and contains(text(), 'Product added to shopping cart')]");
    protected final By cartLabelQuantity = By.id("lblCartCount");
    protected final By productPrice = By.xpath("//span[contains(text(), '$')]/span");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnIncreaseQuantityButton(String quantity){
        for (int i = 1; i < Integer.parseInt(quantity); i++){
            driver.findElement(increaseQuantityButton).click();
        }
    }

    public String getProductPrice(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
        return element.getText();
    }

    public void clickOnAddToCartButton(){
        driver.findElement(addToCartButton).click();
    }

    public boolean isSuccessfulMessageDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return element.isDisplayed();
    }

    public String getCartLabelQuantity(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cartLabelQuantity));
        return element.getText();
    }

    public void selectQuantityAndAddToCard(String itemQuantity){
        clickOnIncreaseQuantityButton(itemQuantity);
        clickOnAddToCartButton();
    }
}
