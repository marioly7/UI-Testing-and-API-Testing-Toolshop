package pages;

import models.Payment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

public class CartPaymentPage extends BasePage {

    protected final By paymentMethodInput = By.id("payment-method");
    protected final By monthlyInstallmentInput = By.id("monthly_installments");
    protected final By confirmOrderButton = By.xpath("//button[contains(text(), 'Confirm')]");
    protected final By paymentSuccessMessage = By.xpath("//div[contains(text(), 'Payment was successful')]");

    public CartPaymentPage(WebDriver driver) {
        super(driver);
    }

    public void selectPaymentMethod(String paymentMethod) {
        driver.findElement(paymentMethodInput).sendKeys(paymentMethod);
    }

    public void selectMonthlyInstallment(String monthlyInstallment) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(monthlyInstallmentInput));
        element.sendKeys(monthlyInstallment);
    }

    public void clickOnConfirmOrderButton(){
        driver.findElement(confirmOrderButton).click();
    }

    public boolean isPaymentSuccessMessageDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentSuccessMessage));
        return element.isDisplayed();
    }

    public void payment(Payment payment) {
        selectPaymentMethod(payment.getPaymentMethod());
        selectMonthlyInstallment(payment.getMontlyInstallments());
        clickOnConfirmOrderButton();
    }
}
