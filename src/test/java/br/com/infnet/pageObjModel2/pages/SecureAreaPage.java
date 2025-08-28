package br.com.infnet.pageObjModel2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecureAreaPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By titulo = By.cssSelector("div.example h2");
    private final By flash = By.id("flash");
    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }
    public String titulo(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titulo)).getText();
    }
    public String mensagem(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flash)).getText();
    }
}
