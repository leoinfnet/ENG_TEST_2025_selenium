package br.com.infnet.pageObjModel2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By user = By.id("username");
    private final By pass = By.id("password");
    private final By btnLogin = By.cssSelector("button[type='submit']");
    private final By flash = By.id("flash");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public LoginPage abrir(){
        driver.get("https://the-internet.herokuapp.com/login");
        return this;
    }
    public LoginPage preencherUsuario(String usuario){
        wait.until(ExpectedConditions.visibilityOfElementLocated(user)).clear();
        driver.findElement(user).sendKeys(usuario);
        return this;

    }
    public LoginPage preencherSenha(String senha){
        wait.until(ExpectedConditions.visibilityOfElementLocated(pass)).clear();
        driver.findElement(pass).sendKeys(senha);
        return this;
    }
    public SecureAreaPage logarComSucesso(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin)).click();
        return new SecureAreaPage(driver);

    }
    public LoginPage submterEsperandoError(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin)).click();
        return this;

    }
    public String mensagem(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flash)).getText();
    }
}
