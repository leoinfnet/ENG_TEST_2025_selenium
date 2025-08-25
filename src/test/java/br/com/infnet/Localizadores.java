package br.com.infnet;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Localizadores extends BaseTest{
    private static final Integer SLEEP = 2000;
    @Test
    public void deveBuscarNaWiki() throws InterruptedException {
        driver.get("https://wikipedia.org");

        WebElement searchInput = driver.findElement(By.id("searchInput"));
        Point location = searchInput.getLocation();
        System.out.println("Location: " + location);
        searchInput.sendKeys("Botafogo FR");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(SLEEP);
        assertThat(driver.getTitle())
                .containsIgnoringCase("botafogo de futebol e regatas");
        driver.navigate().back();

//        Thread.sleep(SLEEP);
//        driver.findElement(By.className("searchInput")).sendKeys("Junit"); //Erro nao acha o elemento
//        Thread.sleep(SLEEP);
//        assertThat(driver.getTitle())
//                .containsIgnoringCase("junit");

        List<WebElement> links = driver.findElements(By.tagName("a"));
        WebElement webElement = links.get(0);
        Optional<WebElement> wikivoyage = links.stream()
                .filter(link -> link.getText().contains("Wikivoyage"))
                .findFirst();

        Thread.sleep(SLEEP);
        driver.navigate().back();


        assertThat(links.size()).isGreaterThan(10);
        Thread.sleep(SLEEP);

        WebElement element = driver.findElement(By.xpath("//input[contains(@id,'search')]"));
        element.sendKeys("Software Testing");

    }
    @Test
    public void cssOperadores() throws InterruptedException {
        driver.get("https://wikipedia.org");
//        WebElement campo = driver.findElement(By.cssSelector("input[placeholder^='Sear']"));
//        campo.clear();
//        campo.sendKeys("Python");
//        Thread.sleep(SLEEP);
        WebElement element = driver.findElement(By.xpath("//a/strong[text()='English']"));



        driver.findElement(By.cssSelector(".central-featured-lang:nth-of.type(1) a"));
    }
    @Test
    public void xpathOperadores() throws InterruptedException {
        driver.get("https://wikipedia.org");
//        WebElement campo = driver.findElement(By.cssSelector("input[placeholder^='Sear']"));
//        campo.clear();
//        campo.sendKeys("Python");
//        Thread.sleep(SLEEP);
        WebElement element = driver.findElement(By.xpath("//a/strong[text()='English']"));


        driver.findElement(By.xpath("//input[starts-with(@id, 'search')]"));
        driver.findElement(By.xpath("//input[containts(@class, 'central-featured-land')]"));


    }
    @Test
    public void linksTextsSubstrings() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/");

        driver.findElement(By.linkText("A/B Testing")).click();
        Thread.sleep(1000);
        assertThat(driver.getCurrentUrl()).contains("/abtest");

        driver.navigate().back();
        driver.findElement(By.partialLinkText("Add/Remove")).click();
        Thread.sleep(1000);

    }

    @Test
    public void escopoDentroDeUmContainer() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        Thread.sleep(1000);
        WebElement form = driver.findElement(By.tagName("form"));
        form.findElement(By.name("my-text")).sendKeys("Leonardo");
        Thread.sleep(1000);

    }
    @Test
    public void trabalhandoComListas() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        Thread.sleep(1000);

        List<WebElement> checks = driver.findElements(By.cssSelector("input[type='checkbox']"));

        for(WebElement c: checks){
            if(!c.isSelected()) c.click();
            Thread.sleep(1_000);
        }

    }
    @Test
    public void trabalhandoComTabelas() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/tables");
        Thread.sleep(1000);

        WebElement element = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[2]"));
        var texto = element.getText();
        System.out.println(texto);
        assertThat(texto).isNotBlank();

        List<WebElement> elements = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr[2]"));
        for(WebElement e: elements){
            e.getText();
        }


    }

}
