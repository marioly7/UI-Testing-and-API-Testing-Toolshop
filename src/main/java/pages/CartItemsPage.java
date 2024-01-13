package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

public class CartItemsPage extends BasePage {

    protected final By totalAmount = By.xpath("//tfoot/tr[1]/td[5]");
    protected final By proceedToCheckoutButton = By.xpath("//button[contains(text(), 'Proceed to checkout') and not(@disabled)]");
    public CartItemsPage(WebDriver driver) {
        super(driver);
    }


    public String getTotalAmount(){
        return driver.findElement(totalAmount).getText();
    }


    public boolean isItemDisplayed(Product product){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='product-title' and contains(text(), '" + product.getExpectedItem() +  "')]")));
        return element.isDisplayed();
    }

    public String getItemQuantity(Product product){
        return driver.findElement(By.xpath("//tr[td/span[@class='product-title' and contains(text(), '" + product.getExpectedItem() +  "')]]/td/input[@class='form-control quantity']"))
                .getAttribute("value");
    }

    public String getItemPrice(Product product){
        return driver.findElement(By.xpath("//tr[td/span[@class='product-title' and contains(text(), '" + product.getExpectedItem() +  "')]]/td[4]/span"))
                .getText();
    }

    public String getItemTotal(Product product){
        return driver.findElement(By.xpath("//tr[td/span[@class='product-title' and contains(text(), '" + product.getExpectedItem() +  "')]]/td[5]/span"))
                .getText();
    }

    public void clickOnProceedToCheckoutButton(){
        driver.findElement(proceedToCheckoutButton).click();
    }

}
