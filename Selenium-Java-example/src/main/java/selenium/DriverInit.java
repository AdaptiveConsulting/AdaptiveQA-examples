package selenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import selenium.config.DriverSelector;
import selenium.listeners.ScreenCaptureListener;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenCaptureListener.class)
public class DriverInit {

    private static List<DriverSelector> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverSelector>());
    private static ThreadLocal<DriverSelector> driverFactory;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverFactory = new ThreadLocal<DriverSelector>() {
            @Override
            protected DriverSelector initialValue() {
                DriverSelector driverFactory = new DriverSelector();
                webDriverThreadPool.add(driverFactory);
                return driverFactory;
            }
        };
    }

    @BeforeMethod
    public static void maximizeBrowser() throws Exception {
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() throws Exception {
        return driverFactory.get().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        for (DriverSelector driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }
}