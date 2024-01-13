package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

public class HomePage extends BasePage {

    protected final By sortOption = By.className("form-select");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private void selectSortOption(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(sortOption));
        element.sendKeys("Price (Low - High)");
    }

    private void selectOption(Product product){
        String option = product.getCategory();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), '" + option + "')]/input")));
        element.click();
    }

    private void clickOnItem(String item){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(), '" + item + "')]")));
        element.click();
    }

    public void selectFiltersAndItem(Product product){
        selectSortOption();
        selectOption(product);
        clickOnItem(product.getExpectedItem());
    }
}
