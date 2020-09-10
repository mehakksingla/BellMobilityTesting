package testcases;

import config.Config;
import driverManager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class HomePageTest {

    WebDriver driver;

    @Test
    public void tc1() {
        String b1 = Config.getInstance().getProperty("browser");
        Assert.assertEquals(b1, "chrome");

    }


    @Test
    public void openBell() throws IOException, InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        driver.get(Config.getInstance().getProperty("app.url"));
        String openApp = Utils.captureScreenshot(driver, "OpenApp", true);
        Thread.sleep(10000);
        driver.quit();
    }
}
