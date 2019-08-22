import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class firstTest {
    WebDriver driver;

    @Before
    public void startBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void closeBrwoser(){
        driver.quit();
    }

    @Test
    public void firstTestCase(){
        driver.get("https://www.google.com");

    }

}

