package br.com.infnet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseTest {
    protected WebDriver driver;
    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        //WebDriverManager.edgedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");
    //    chromeOptions.addArguments("--headless=new");
      //  chromeOptions.addArguments("--disable-gpu");

        driver = new ChromeDriver(chromeOptions);
        //driver = new EdgeDriver();
//        driver.manage().window()
//                .setSize(new Dimension(600,800));
//
//        driver.manage().window().setPosition(new Point(1920,0));
    }
    @BeforeEach
    public void tearDown(){
        if(driver != null ){
            driver.quit();
        }
    }
}
