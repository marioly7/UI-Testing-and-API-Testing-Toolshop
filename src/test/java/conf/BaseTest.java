package conf;

import com.aventstack.extentreports.Status;
import helpers.ReportManager;
import helpers.ScreenShotHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected WebDriver webDriver;
    public static String currentBrowser = "";
    //private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public static void setupSuite() throws Exception {
        ReportManager.init("reports", "PurchaseSuite");
    }

    @BeforeMethod
    @Parameters({"url", "browser"})
    public void setup(ITestResult iTestResult, String url, String browser) throws Exception {
        ReportManager.getInstance().startTest(iTestResult.getMethod().getDescription());
        switch (browser) {
            case "chrome":
                currentBrowser = "chrome";
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                currentBrowser = "firefox";
                System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new Exception(browser + " no soportado");
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.get(url);
        //log.info("Navigate to {}", url);
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "Navigate to login page");
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) throws InterruptedException {
        try {
            switch (iTestResult.getStatus()) {
                case ITestResult.FAILURE:
                    ReportManager.getInstance().getTest().log(Status.FAIL, "Test failes");
                    ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.FAIL, "Failure Image");
                    System.out.println("ITEST STATUS FAILURE SWTICH");
                    break;
                case ITestResult.SUCCESS:
                    ReportManager.getInstance().getTest().log(Status.PASS, "Test passes");
                    break;
                case ITestResult.SKIP:
                    ReportManager.getInstance().getTest().log(Status.SKIP, "Test skipped");
                    break;
                default:
                    ReportManager.getInstance().getTest().log(Status.FAIL, "Test incomplete");
            }

            if (iTestResult.getStatus() != ITestResult.SUCCESS && iTestResult.getThrowable() != null) {
                System.out.println("ITEST STATUS != FAILURE");
                ReportManager.getInstance().getTest().log(Status.FAIL, iTestResult.getThrowable().getMessage());
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.FAIL, "Failure Image");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ITEST CATCH");
            System.out.println("Exception caught: " + e.getClass().getName() + " - " + e.getMessage());
            System.out.println("ITEST CATCH");
        }finally {
            if(webDriver != null)
                webDriver.quit();
            Thread.sleep(1000);
        }
    }

    @AfterSuite
    public static void tearDownSuite() {
        ReportManager.getInstance().flush();
    }
}
