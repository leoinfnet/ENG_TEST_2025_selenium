package br.com.infnet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class HelloWorld {
    private WebDriver driver;
    @BeforeEach
    void setup(){
        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();

        //driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver.manage().window()
                .setSize(new Dimension(600,900));
        driver.manage().window().setPosition(new Point(1920,0));

    }

    @Test
    void deveAbrirWiki(){
        driver.get("https://wikipedia.org");
        String title = driver.getTitle();
        System.out.println("Titulo: " + title);
    }
    @BeforeEach
    void tearDown(){
        if(driver != null) driver.quit();
    }
}
