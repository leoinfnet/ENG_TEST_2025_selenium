package br.com.infnet;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Navigation extends BaseTest{
    @Test
    void deveNavegarNoHistorico() throws InterruptedException {
        driver.get("https://example.org/");
        Thread.sleep(1000);

        driver.navigate().to("https://wikipedia.org/");
        Thread.sleep(1000);
        driver.navigate().to("https://developer.mozilla.org/");

        Long lengthHistorico = (Long) ((JavascriptExecutor) driver)
                .executeScript("return window.history.length");
        assertTrue(lengthHistorico >= 3);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        Thread.sleep(2000);
        driver.navigate().refresh();


    }
}
