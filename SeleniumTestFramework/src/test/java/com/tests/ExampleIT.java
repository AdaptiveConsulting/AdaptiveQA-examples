package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjectModel.pages.duckDuckGoHome.duckDuckGoHomePage;
import selenium.DriverInit;

public class ExampleIT extends DriverInit {

    private final String defaultBaseUrl = "https://duckduckgo.com";
    private String baseUrl = System.getProperty("baseUrl", defaultBaseUrl);

/*
Example of using WebDriver and TestNG assertions within the test
 */
    @Test(priority = 1)
    public void duckDuckGoPumpkinSoupSearch() throws Exception {

        WebDriver driver = getDriver();
        System.out.println("Running Example Test 1");
        driver.get(baseUrl );
        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));
        element.clear();
        element.sendKeys("Pumpkin Soup");
        element.submit();
        System.out.println("Page title equal: "+ driver.getTitle());
        Assert.assertEquals(driver.getTitle().toUpperCase(), "Pumpkin Soup at DuckDuckGo".toUpperCase());
    }
    /*
    Example of using WebDriver and TestNG with lambda expression and dynamic wait
     */
    @Test(priority = 2)
    public void duckDuckGoCottonCandy() throws Exception {

        WebDriver driver = getDriver();
        System.out.println("Running Example Test 2");
        driver.get(baseUrl );
        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));
        element.clear();
        element.sendKeys("Cotton Candy");
        element.submit();
        System.out.println("Page title equal: "+ driver.getTitle());

        (new WebDriverWait(driver, 10)).until(
                (ExpectedCondition<Boolean>)
                        d -> d.getTitle().toLowerCase().contains("cotton"));
    }

    /*
    Example of using WebDriver and TestNG and Page Object Model
     */
    @Test(priority = 3)
    public  void duckDuckGoDynamicSearch() throws Exception {
        WebDriver driver = getDriver();
        System.out.println("Running Example Test 3");
        driver.get(baseUrl );
        duckDuckGoHomePage page = new duckDuckGoHomePage(driver);
        page.searchForContent("dark chocolate");
    }
}
