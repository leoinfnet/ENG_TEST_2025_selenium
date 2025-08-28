package br.com.infnet.pageObjectModel1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultadoBuscaPage extends BasePage{
    private final By tituloEhPrimeiroResultado = By.id("firstHeading");
    public ResultadoBuscaPage(WebDriver driver) {
        super(driver);
    }
    public String lerTituloPrincipal(){
        return $(tituloEhPrimeiroResultado).getText();
    }
}
