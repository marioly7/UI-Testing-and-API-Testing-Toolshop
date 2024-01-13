package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

public class LoginPage extends BasePage {

    protected final By registerOption = By.xpath("//a[contains(text(), 'Register your account')]");
    protected final By emailField = By.id("email");
    protected final By passwordField = By.id("password");
    protected final By loginButton = By.className("btnSubmit");
    protected final By loginMessage = By.xpath("//h3[contains(text(), 'Login')]");
    protected final By loginErrorMessage = By.xpath("//div[contains(text(), 'Invalid email or password')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(User user) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Boolean element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginMessage)).isDisplayed();
        if (element) {
            driver.findElement(emailField).sendKeys(user.getEmail());
            driver.findElement(passwordField).sendKeys(user.getPassword());
        }

        clickOnLoginButton();
    }

    public boolean isEmailOrPasswordErrorDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage)).isDisplayed();
        if (driver.findElement(loginErrorMessage).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void clickOnRegisterOption() {
        driver.findElement(registerOption).click();
    }

    private void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

}



