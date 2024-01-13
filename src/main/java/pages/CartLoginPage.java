package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

public class CartLoginPage extends BasePage {

    protected final By registerOption = By.xpath("//a[contains(text(), 'Register your account')]");
    protected final By welcomeToLoggedUserMessage = By.xpath("//p[contains(text(), 'you are already logged in. You can proceed to checkout.')]");
    protected final By proceedToCheckoutButton = By.xpath("//button[contains(text(), 'Proceed to checkout') and (@data-test='proceed-2')]");

    public CartLoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnRegisterOption(){
        driver.findElement(registerOption).click();
    }

    public boolean isWelcomeToLoggedUserMessageDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeToLoggedUserMessage));
        return element.isDisplayed();
    }

    public void clickOnProceedToCheckoutButton(){
        driver.findElement(proceedToCheckoutButton).click();
    }
}
