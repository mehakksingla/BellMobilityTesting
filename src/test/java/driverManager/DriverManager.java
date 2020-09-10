package driverManager;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    //define the driver
    private WebDriver driver;

    //define the instance to restrict the browser one at a time
    //we will change it when grid will be added
    private static DriverManager instance;

    private DriverManager(){
        init();

    }

    //define the init method so that we dont have to write everything in constructor
    private void init(){
        if (Config.getInstance()
                .getProperty("browser")
                .equalsIgnoreCase(BrowserTypes.CHROME)){
            System.setProperty("webdriver.chrome.driver",
                    Config.getInstance().getProperty("chrome.executable"));
            driver = new ChromeDriver();

        } else if (Config.getInstance().getProperty("browser").equalsIgnoreCase(BrowserTypes.FIREFOX)){
            System.setProperty("webdriver.gecko.driver", Config.getInstance().getProperty("firefox.executable"));
            driver = new FirefoxDriver();

//        } else if (Config.getInstance().getProperty("browser").equalsIgnoreCase(BrowserTypes.OPERA)) {
//            System.setProperty("webdriver.Opera.Setup", Config.getInstance().getProperty("opera.executable"));
//            driver = new OperaDriver();
//
//        } else if (Config.getInstance().getProperty("browser").equalsIgnoreCase(BrowserTypes.INTERNET_EXPLORER)) {
//            System.setProperty("webdriver.IE.DriverServer", Config.getInstance().getProperty("ie.executable"));
//            driver = new InternetExplorerDriver();
//
//        } else if (Config.getInstance().getProperty("browser").equalsIgnoreCase(BrowserTypes.SAFARI)) {
//            System.setProperty("webdriver.Safari.Setup", Config.getInstance().getProperty("safari.executable"));
//            driver = new SafariDriver();
//
//        } else if (Config.getInstance().getProperty("browser").equalsIgnoreCase(BrowserTypes.EDGE)) {
//            System.setProperty("webdriver.msedge.driver", Config.getInstance().getProperty("edge.executable"));
//            driver = new EdgeDriver();

        } else {
            System.setProperty("webdriver.chrome.driver", Config.getInstance().getProperty("chrome.executable"));
            driver = new ChromeDriver();

        }
    }

    public synchronized static WebDriver getDriver(){
        if (instance == null){
            instance = new DriverManager();
        }
        return instance.driver;
    }

}
