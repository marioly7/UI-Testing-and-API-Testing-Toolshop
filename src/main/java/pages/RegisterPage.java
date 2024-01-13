package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.conf.BasePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegisterPage extends BasePage {

    protected final By firstnameInput = By.id("first_name");
    protected final By lastnameInput = By.id("last_name");
    protected final By addressInput = By.id("address");
    protected final By dateOfBirthInput = By.id("dob");
    protected final By emailInput = By.id("email");
    protected final By postCodeInput = By.id("postcode");
    protected final By cityInput = By.id("city");
    protected final By stateInput = By.id("state");
    protected final By phoneInput = By.id("phone");
    protected final By passwordInput = By.id("password");
    protected final By countryInput = By.id("country");
    protected final By registerButton = By.className("btnSubmit");
    protected final By firstNameError = By.xpath("//div[@data-test = 'first-name-error']/div");
    protected final By lastNameError = By.xpath("//div[@data-test = 'last-name-error']/div");
    protected final By addressError = By.xpath("//div[@data-test = 'address-error']/div");
    protected final By dateOfBirthError = By.xpath("//div[@data-test = 'dob-error']/div");
    protected final By emailError = By.xpath("//div[@data-test = 'email-error']/div");
    protected final By postCodeError = By.xpath("//div[@data-test = 'postcode-error']/div");
    protected final By cityError = By.xpath("//div[@data-test = 'city-error']/div");
    protected final By stateError = By.xpath("//div[@data-test = 'state-error']/div");
    protected final By phoneError = By.xpath("//div[@data-test = 'phone-error']/div");
    protected final By passwordError = By.xpath("//div[@data-test = 'password-error']/div");
    protected final By countryError = By.xpath("//div[@data-test = 'country-error']/div");
    protected final By registerMessage = By.xpath("//h3[contains(text(), 'Customer registration')]");
    protected final By helpBlockError = By.className("help-block");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void registerUser(User user, String browser) throws InterruptedException {

        switch (browser) {
            case "chrome":
                addUserChrome(user);
                break;
            case "firefox":
                addUserFirefox(user);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }
    }

    private void addUserChrome(User user) throws InterruptedException {
        driver.findElement(firstnameInput).sendKeys(user.getFirstname());
        driver.findElement(lastnameInput).sendKeys(user.getLastname());
        driver.findElement(dateOfBirthInput).click();

        String[] dateParts = user.getDateOfBirth().split("/");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        Actions actions = new Actions(driver);
        for (int i = 0; i < Integer.parseInt(day); i++) {
            actions.sendKeys(Keys.ARROW_UP).build().perform();
        }
        actions.sendKeys(Keys.TAB).build().perform();

        for (int i = 0; i < Integer.parseInt(month); i++) {
            actions.sendKeys(Keys.ARROW_UP).build().perform();
        }
        actions.sendKeys(Keys.TAB).build().perform();

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String yearString = currentDate.format(formatter);

        for (int i = 0; i < Integer.parseInt(yearString) - Integer.parseInt(year) + 1; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        }


        driver.findElement(addressInput).sendKeys(user.getAddress());
        driver.findElement(postCodeInput).sendKeys(user.getPostCode());
        driver.findElement(cityInput).sendKeys(user.getCity());
        driver.findElement(stateInput).sendKeys(user.getState());
        driver.findElement(countryInput).sendKeys(user.getCountry());
        driver.findElement(phoneInput).sendKeys(user.getPhone());
        driver.findElement(emailInput).sendKeys(user.getEmail());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        clickOnRegisterButton(user.getPassword());
    }

    private void addUserFirefox(User user) throws InterruptedException {
        driver.findElement(firstnameInput).sendKeys(user.getFirstname());
        driver.findElement(lastnameInput).sendKeys(user.getLastname());
        driver.findElement(dateOfBirthInput).click();

        String[] dateParts = user.getDateOfBirth().split("/");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");
        String dayString = currentDate.format(formatterDay);
        String yearString = currentDate.format(formatterYear);
        String monthString = currentDate.format(formatterMonth);

        Integer dayInt = (Integer.parseInt(dayString) % 31) + 1;

        Integer dayAux = Integer.parseInt(day) - dayInt;

        Keys key = dayAux > 0 ? Keys.ARROW_UP : Keys.ARROW_DOWN;

        Actions actions = new Actions(driver);

        actions.sendKeys(Keys.ARROW_UP).build().perform();

        for (int i = 0; i < Math.abs(dayAux); i++) {
            actions.sendKeys(key).build().perform();
        }

        actions.sendKeys(Keys.TAB).build().perform();

        Integer monthInt = (Integer.parseInt(monthString) % 12) + 1;

        Integer monthAux = Integer.parseInt(month) - monthInt;

        Keys key1 = monthAux > 0 ? Keys.ARROW_UP : Keys.ARROW_DOWN;

        actions.sendKeys(Keys.ARROW_UP).build().perform();

        for (int i = 0; i < Math.abs(monthAux); i++) {
            actions.sendKeys(key1).build().perform();
        }

        actions.sendKeys(Keys.TAB).build().perform();

        actions.sendKeys(Keys.ARROW_UP).build().perform();

        for (int i = 0; i < Integer.parseInt(yearString) - Integer.parseInt(year) + 1; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        }

        driver.findElement(addressInput).sendKeys(user.getAddress());
        driver.findElement(postCodeInput).sendKeys(user.getPostCode());
        driver.findElement(cityInput).sendKeys(user.getCity());
        driver.findElement(stateInput).sendKeys(user.getState());
        driver.findElement(countryInput).sendKeys(user.getCountry());
        driver.findElement(phoneInput).sendKeys(user.getPhone());
        driver.findElement(emailInput).sendKeys(user.getEmail());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        clickOnRegisterButton(user.getPassword());
    }

    private void clickOnRegisterButton(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(passwordInput, "value", password));
        driver.findElement(registerButton).click();
    }

    public boolean isDateOfBirthInvalidErrorDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(helpBlockError)).isDisplayed();
        if(driver.findElement(helpBlockError).getText().equals("Customer must be 18 years old.")){
            return true;
        }else{
            return false;
        }
    }
}
