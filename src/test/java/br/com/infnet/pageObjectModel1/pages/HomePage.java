package br.com.infnet.pageObjectModel1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private final By campoBusca = By.id("searchInput");
    private final By botaoBuscar = By.cssSelector("button[type='submit']");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public HomePage abrir(){
        driver.get("https://www.wikipedia.org/");
        return this;
    }
    public ResultadoBuscaPage buscarPor(String termo){
        type(campoBusca,termo);
        click(botaoBuscar);
        return new ResultadoBuscaPage(driver);
    }
}
