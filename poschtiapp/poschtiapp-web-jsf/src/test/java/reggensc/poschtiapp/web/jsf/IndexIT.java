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

    @Before
    public void setUp() {
        // driver = new ChromeDriver();
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/poschtiapp-web-jsf/");
    }

    @Test
    public void testGetPage() {
        WebElement element = driver.findElement(By.id("j_idt4"));
        String watermarkText = element.getAttribute("placeholder");
        Assert.assertEquals("Watermark here", watermarkText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
