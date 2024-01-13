package purchaseProducts;

import conf.BaseTest;
import data.GetPayment;
import data.GetProduct;
import data.GetUser;
import loaders.ProductLoader;
import models.Product;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;


public class PurchaseProductsTest extends BaseTest {

    protected final String jsonFilePath = "src/test/resources/products.json";
    protected final Product productOne = ProductLoader.getProductByKey(jsonFilePath, "Chisels Set");
    protected final Product productTwo = ProductLoader.getProductByKey(jsonFilePath, "Belt Sander");

    @Test(description = "Purchase products successful")
    public void purchaseProductsSuccessful() throws InterruptedException {
        String filePath = "src/test/resources/products.json";
        File file = new File(filePath);
        System.out.println("Ruta absoluta del archivo: " + file.getAbsolutePath());

        HomePage homePage = new HomePage(webDriver);
        homePage.selectFiltersAndItem(productOne);

        ProductPage productPage = new ProductPage(webDriver);
        productPage.selectQuantityAndAddToCard(productOne.getExpectedQuantity());

        assertProductAddedToCart(productOne.getExpectedQuantity(), productOne.getExpectedItem());

        NavBarPage navBarPage = new NavBarPage(webDriver);
        navBarPage.clickOnHomeOption();
        homePage.selectFiltersAndItem(productTwo);

        productPage.selectQuantityAndAddToCard(productTwo.getExpectedQuantity());

        assertProductAddedToCart("6", productTwo.getExpectedItem());

        navBarPage.clickOnCartOption();

        CartItemsPage cartItemsPage = new CartItemsPage(webDriver);

        assertItemsInCart();
        assertItemsInCartDetails(GetProduct.getProductsTotal(productOne, productTwo));

        cartItemsPage.clickOnProceedToCheckoutButton();

        CartLoginPage cartLoginPage = new CartLoginPage(webDriver);
        cartLoginPage.clickOnRegisterOption();

        RegisterPage registerPage = new RegisterPage(webDriver);
        User user = GetUser.getValidUser();
        registerPage.registerUser(user, currentBrowser);

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(user);

        AccountPage accountPage = new AccountPage(webDriver);

        Assert.assertTrue(accountPage.isMyAccountTitleDisplayed(), "El mensaje de cuenta no se encuentra visible");

        navBarPage.clickOnCartOption();

        assertItemsInCart();

        cartItemsPage.clickOnProceedToCheckoutButton();

        Assert.assertTrue(cartLoginPage.isWelcomeToLoggedUserMessageDisplayed(), "El mensaje de bienvenida no se encuentra visible");

        cartLoginPage.clickOnProceedToCheckoutButton();
        CartAddressPage cartAddressPage = new CartAddressPage(webDriver);

        assertUserAddressData(user);

        cartAddressPage.clickOnProceedToCheckoutButton();

        CartPaymentPage cartPaymentPage = new CartPaymentPage(webDriver);
        cartPaymentPage.payment(GetPayment.getValidBuyNowAndPayLaterPayment());

        Assert.assertTrue(cartPaymentPage.isPaymentSuccessMessageDisplayed(), "El mensaje de confirmación de orden no se encuentra visible");
    }

    @Test(description = "Purchase products with invalid Login credentials")
    public void purchaseProductsInvalidLoginCredentials() throws InterruptedException {
        HomePage homePage = new HomePage(webDriver);
        homePage.selectFiltersAndItem(productOne);

        ProductPage productPage = new ProductPage(webDriver);
        productPage.selectQuantityAndAddToCard(productOne.getExpectedQuantity());

        assertProductAddedToCart(productOne.getExpectedQuantity(), productOne.getExpectedItem());

        NavBarPage navBarPage = new NavBarPage(webDriver);
        navBarPage.clickOnHomeOption();
        homePage.selectFiltersAndItem(productTwo);

        productPage.selectQuantityAndAddToCard(productTwo.getExpectedQuantity());

        assertProductAddedToCart("6", productTwo.getExpectedItem());

        navBarPage.clickOnCartOption();

        CartItemsPage cartItemsPage = new CartItemsPage(webDriver);

        assertItemsInCart();
        assertItemsInCartDetails(GetProduct.getProductsTotal(productOne, productTwo));

        cartItemsPage.clickOnProceedToCheckoutButton();

        CartLoginPage cartLoginPage = new CartLoginPage(webDriver);
        cartLoginPage.clickOnRegisterOption();

        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.registerUser(GetUser.getValidUser(), currentBrowser);

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(GetUser.getInvalidUser());
        Assert.assertTrue(loginPage.isEmailOrPasswordErrorDisplayed(), "El mensaje de error de email o password incorrecto no se encuentra visible");
    }

    @Test(description = "Purchase products with invalid Register (invalid date of birth)")
    public void purchaseProductsInvalidDateOfBirth() throws InterruptedException {
        HomePage homePage = new HomePage(webDriver);
        homePage.selectFiltersAndItem(productOne);

        ProductPage productPage = new ProductPage(webDriver);
        productPage.selectQuantityAndAddToCard(productOne.getExpectedQuantity());

        assertProductAddedToCart(productOne.getExpectedQuantity(), productOne.getExpectedItem());

        NavBarPage navBarPage = new NavBarPage(webDriver);
        navBarPage.clickOnHomeOption();
        homePage.selectFiltersAndItem(productTwo);

        productPage.selectQuantityAndAddToCard(productTwo.getExpectedQuantity());

        assertProductAddedToCart("6", productTwo.getExpectedItem());

        navBarPage.clickOnCartOption();

        CartItemsPage cartItemsPage = new CartItemsPage(webDriver);

        assertItemsInCart();
        assertItemsInCartDetails(GetProduct.getProductsTotal(productOne, productTwo));

        cartItemsPage.clickOnProceedToCheckoutButton();

        CartLoginPage cartLoginPage = new CartLoginPage(webDriver);
        cartLoginPage.clickOnRegisterOption();

        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.registerUser(GetUser.getUserWithInvalidDateOfBirth(), currentBrowser);
        Assert.assertTrue(registerPage.isDateOfBirthInvalidErrorDisplayed(), "El mensaje de fecha de nacimiento inválido no se encuentra visible");
    }

    private void assertUserAddressData(User user) {
        CartAddressPage cartAddressPage = new CartAddressPage(webDriver);
        Assert.assertEquals(cartAddressPage.getAddressInputValue(user.getAddress()), user.getAddress(), "Error de dirección: La dirección no es correcta");
        Assert.assertEquals(cartAddressPage.getCityInputValue(user.getCity()), user.getCity(), "Error de ciudad: La ciudad no es correcta");
        Assert.assertEquals(cartAddressPage.getStateInputValue(user.getState()), user.getState(), "Error de estado: El estado no es correcto");
        Assert.assertEquals(cartAddressPage.getPostCodeInputValue(user.getPostCode()), user.getPostCode(), "Error de código postal: El código postal no es correcto");
    }

    private void assertItemsInCartDetails(String expectedTotalPrice) {
        CartItemsPage cartItemsPage = new CartItemsPage(webDriver);
        Assert.assertEquals(cartItemsPage.getItemQuantity(productOne), productOne.getExpectedQuantity(), "Error de cantidad: Thor's hammer no se encuentra en el carrito de compras");
        Assert.assertEquals(cartItemsPage.getItemQuantity(productTwo), productTwo.getExpectedQuantity(), "Error de cantidad: Circular saw no se encuentra en el carrito de compras");
        Assert.assertEquals(cartItemsPage.getItemPrice(productOne), "$" + productOne.getExpectedPrice(), "Error de precio: Thor's hammer no se encuentra en el carrito de compras");
        Assert.assertEquals(cartItemsPage.getItemPrice(productTwo), "$" + productTwo.getExpectedPrice(), "Error de precio: Circular saw no se encuentra en el carrito de compras");
        Assert.assertEquals(cartItemsPage.getItemTotal(productOne), "$" + productOne.getExpectedTotal(), "Error de subtotal: El subtotal Thor's hammer  no es correcto");
        Assert.assertEquals(cartItemsPage.getItemTotal(productTwo), "$" + productTwo.getExpectedTotal(), "Error de subtotal: El subtotal Circular saw no es correcto");
        Assert.assertEquals(cartItemsPage.getTotalAmount(), "$" + expectedTotalPrice, "Error de total: El total no es correcto");
    }

    private void assertItemsInCart() {
        CartItemsPage cartItemsPage = new CartItemsPage(webDriver);
        Assert.assertTrue(cartItemsPage.isItemDisplayed(productOne),  productTwo.getExpectedItem() + " no se encuentra en el carrito de compras");
        Assert.assertTrue(cartItemsPage.isItemDisplayed(productTwo), productTwo.getExpectedItem() + " no se encuentra en el carrito de compras");
    }

    private void assertProductAddedToCart(String expectedItemQuantity, String expectedItemName) {
        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertTrue(productPage.isSuccessfulMessageDisplayed(), "El producto " + expectedItemName +  " no fue agregado al carrito de compras");
        Assert.assertEquals(productPage.getCartLabelQuantity(), expectedItemQuantity, "Error de cantidad: El producto " + expectedItemName + " no fue agregado al carrito de compras");
    }
}
