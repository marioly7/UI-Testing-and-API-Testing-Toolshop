package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

public class NavBarPage extends BasePage {

    protected final By signUpOption = By.xpath("//a[contains(text(), 'Sign in')]");
    protected final By homeOption = By.xpath("//a[contains(text(), 'Home')]");
    protected final By cartOption = By.xpath("//a/i[@class='fa fa-shopping-cart px-1']");

    public NavBarPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCartOption(){
        driver.findElement(cartOption).click();
    }

    public void clickOnSignUpOption(){
        driver.findElement(signUpOption).click();
    }

    public void clickOnHomeOption(){
        driver.findElement(homeOption).click();
    }

}
