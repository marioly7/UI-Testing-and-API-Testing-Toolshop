package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

public class AccountPage extends BasePage {

    protected final By myAccountTitle = By.xpath("//h1[contains(text(), 'My account')]");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMyAccountTitleDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountTitle));
        return element.isDisplayed();
    }
}
