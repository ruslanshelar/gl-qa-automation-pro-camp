package tasks;

import com.google.gson.internal.bind.util.ISO8601Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class SecondHomeTask {

    WebDriver driver;

    @Before
    public void startBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void checkMenuElements() throws InterruptedException {

        driver.get("http://demo.litecart.net/admin");
        driver.findElement(By.cssSelector("button[name='login']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#sidebar #box-apps-menu a")));

        List<WebElement> a_elements = driver.findElements(By.cssSelector("#sidebar #box-apps-menu a"));
        List links = new ArrayList();
        a_elements.forEach(a -> links.add(a.getAttribute("href")));

        links.forEach(link -> {
            driver.get((String) link);
            Assert.assertEquals((String) link ,1, driver.findElements(By.cssSelector("h1")).size());

            List<WebElement> selected_item = driver.findElements(By.cssSelector(".selected"));
            List<WebElement> internal_a_elements = selected_item.get(0).findElements(By.cssSelector("a"));
            if (internal_a_elements.size() > 0) {
                List internal_links = new ArrayList();

                internal_a_elements.forEach(a -> internal_links.add(a.getAttribute("href")));

                internal_links.forEach(internal_link -> {
                    driver.get((String) internal_link);
                    Assert.assertEquals((String) internal_link ,1, driver.findElements(By.cssSelector("h1")).size());
                });
            }
        });
    }
}
