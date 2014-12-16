package reggensc.poschtiapp.web.jsf;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class IndexIT {

    private WebDriver driver;
    private final String TEST_PAGE_ADDRESS = "http://localhost:8080/poschtiapp-web-jsf/";

    @Before
    public void setUp() {
        // driver = new ChromeDriver();
        driver = new HtmlUnitDriver();
        driver.get(TEST_PAGE_ADDRESS);
    }

    @Test
    public void testLoginWithoutRememberMe() {
        WebElement usernameInput = driver.findElement(By.id("username"));
        Assert.assertNotNull(usernameInput);
        WebElement passwordInput = driver.findElement(By.id("password"));
        Assert.assertNotNull(passwordInput);
        WebElement loginbutton = driver.findElement(By.id("loginButton"));
        Assert.assertNotNull(loginbutton);

        usernameInput.sendKeys("test.email@testdata.org");
        passwordInput.sendKeys("1234");
        loginbutton.click();

        Assert.assertEquals(TEST_PAGE_ADDRESS, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
