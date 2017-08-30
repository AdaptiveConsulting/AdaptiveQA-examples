package pageObjectModel.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageIsLoaded(String url){

        waitForPageLoad(url);
        return driver.getCurrentUrl().equals(url);
    }

    private void waitForPageLoad(String url) {

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(driver -> String
                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                .equals("complete") && (driver.getCurrentUrl().contains(url)));
    }
}