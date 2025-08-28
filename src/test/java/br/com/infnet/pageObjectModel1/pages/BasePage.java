package br.com.infnet.pageObjectModel1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }
    protected WebElement $(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    protected void type(By locator, String text){
        WebElement el = $(locator);
        el.clear();
        el.sendKeys(text);
    }
    public String getTitle(){return driver.getTitle();}
}
