package br.com.infnet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import static org.assertj.core.api.Assertions.assertThat;

public class Forms  extends BaseTest{
    @Test
    @DisplayName("Exemplo: Preencher texto, limpar e reenviar")
    void devePrencherTexto() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        Thread.sleep(1500);
        WebElement textInput = driver.findElement(By.name("my-text"));
        textInput.sendKeys("Valor inicial!");
        Thread.sleep(1000);
        textInput.clear();
        Thread.sleep(1000);
        textInput.sendKeys("Leonardo");

        assertThat(textInput.getAttribute("value")).isEqualTo("Leonardo");

    }
    @Test
    @DisplayName("Exemplo: DropDown  via select")
    void exemploDropDown() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        Thread.sleep(1500);
        Select select = new Select(driver.findElement(By.name("my-select")));
        select.selectByIndex(3);
        Thread.sleep(1500);
        assertThat(select.getFirstSelectedOption().getText()).isEqualTo("Three");
        select.selectByValue("2");
        Thread.sleep(1500);
        assertThat(select.getFirstSelectedOption().getText()).isEqualTo("Two");

        select.selectByVisibleText("One");
        Thread.sleep(1500);
        assertThat(select.getFirstSelectedOption().getText()).isEqualTo("One");
    }
    @Test
    @DisplayName("Exemplo: Chekbox/Radio")
    void checkBoxRadio() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        Thread.sleep(1500);

        WebElement check = driver.findElement(By.name("my-check"));

//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollTo(0,500)"
//        );

        if(!naoSelecionado(check)) check.click();

        assertThat(check.isSelected()).isFalse();

        Thread.sleep(1500);
        WebElement radio = driver.findElement(By.name("my-radio"));
        if(naoSelecionado(radio)) radio.click();
        Thread.sleep(1500);
        assertThat(radio.isSelected()).isTrue();
    }
    @Test
    @DisplayName("Exemplo: Enviar Formulario e validar Mensagem")
    void exemploDeSubmit() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        Thread.sleep(1500);

        driver.findElement(By.name("my-text")).sendKeys("Leonardo");
        Thread.sleep(1000);

        Select select = new Select(driver.findElement(By.name("my-select")));
        select.selectByVisibleText("Two");
        Thread.sleep(1000);
        WebElement botao = driver.findElement(By.cssSelector("button"));
        new Actions(driver)
                .scrollToElement(botao)
                .click(botao)
                .perform();

        Thread.sleep(1000);

    }


    private boolean naoSelecionado(WebElement check) {
        return !check.isSelected();
    }


}
