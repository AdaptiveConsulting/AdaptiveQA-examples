package pageObjectModel.pages.duckDuckGoHome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjectModel.pages.BasePage;

public class duckDuckGoHomePage extends BasePage {

    public duckDuckGoHomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(name = "q")
    private WebElement searchField;

    public void searchForContent(String content){
        this.searchField.clear();
        this.searchField.sendKeys(content);
        this.searchField.submit();
        System.out.println("Page title is: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle().toUpperCase() ,(content+ " at duckduckgo").toUpperCase());

    }


}
