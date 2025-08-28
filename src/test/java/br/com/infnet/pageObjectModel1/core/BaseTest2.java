package br.com.infnet.pageObjectModel1.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest2 {
    protected WebDriver driver;
    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window()
                .setSize(new Dimension(1000,800));
//
        driver.manage().window().setPosition(new Point(1920,0));
    }
    @BeforeEach
    public void tearDown(){
        if(driver != null ){
            driver.quit();
        }
    }
}
