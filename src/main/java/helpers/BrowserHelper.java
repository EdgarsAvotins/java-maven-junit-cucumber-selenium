package helpers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BrowserHelper {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void initializeDriver() {
        if (driverThreadLocal.get() == null) {
            String chosenBrowser = System.getProperty("browser");
            if (Objects.equals(chosenBrowser, "chrome")) {
                System.out.println("===INITIALIZING CHROME");
//                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
                driverThreadLocal.set(new ChromeDriver());
            } else if (Objects.equals(chosenBrowser, "firefox-headless")) {
                System.out.println("===INITIALIZING FIREFOX HEADLESS");
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true); //DEPRECATED?
//                options.addArguments("--headless=new"); // Doesn't work, version too low?
                driverThreadLocal.set(new FirefoxDriver(options));
            } else {
                System.out.println("===INITIALIZING FIREFOX");
                driverThreadLocal.set(new FirefoxDriver());
            }
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void navigateTo(String url) {
        getDriver().navigate().to(url);
    }

    public static void closeDriver() {
        if (driverThreadLocal.get() != null) {
            System.out.println("===QUITTING");
            driverThreadLocal.get().quit();
            driverThreadLocal.set(null);
        }
    }
}