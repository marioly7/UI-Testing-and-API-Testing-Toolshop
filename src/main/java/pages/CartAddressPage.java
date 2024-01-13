package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

public class CartAddressPage extends BasePage {

    protected final By proceedToCheckoutButton = By.xpath("//button[contains(text(), 'Proceed to checkout') and (@data-test='proceed-3')]");
    protected final By addressInput = By.id("address");
    protected final By postCodeInput = By.id("postcode");
    protected final By cityInput = By.id("city");
    protected final By stateInput = By.id("state");
    public CartAddressPage(WebDriver driver) {
        super(driver);
    }
    public String getAddressInputValue(String address){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(addressInput, "value", address));
        return driver.findElement(addressInput).getAttribute("value");
    }
    public String getPostCodeInputValue(String postCode){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(postCodeInput, "value", postCode));
        return driver.findElement(postCodeInput).getAttribute("value");
    }

    public String getCityInputValue(String city){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(cityInput, "value", city));
        return driver.findElement(cityInput).getAttribute("value");
    }

    public String getStateInputValue(String state){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(stateInput, "value", state));
        return driver.findElement(stateInput).getAttribute("value");
    }
    public void clickOnProceedToCheckoutButton(){
        driver.findElement(proceedToCheckoutButton).click();
    }
}
