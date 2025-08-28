package br.com.infnet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Forms2 extends BaseTest {
    @Test
    @DisplayName("Deve testar: https://demoqa.com/automation-practice-form ")
    void testaFormComplexo() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");
        //Thread.sleep(1600);
        driver.findElement(By.id("firstName")).sendKeys("Leonardo");
        driver.findElement(By.id("lastName")).sendKeys("Gloria");
        driver.findElement(By.id("userEmail")).sendKeys("teste@teste.com");
      //  Thread.sleep(1600);

        driver.findElement(By
                .xpath("//label[contains(@for,'gender-radio')][normalize-space()='Male']"))
                .click();
       // Thread.sleep(1600);

        driver.findElement(By.id("userNumber")).sendKeys("99989795963");

        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));




//        new Actions(driver)
//                .scrollToElement(dateOfBirth)
//                .click()
//                .perform();
//
//        new Actions(driver)
//                .scrollToElement(dateOfBirth)
//                .keyDown(Keys.CONTROL)
//                .sendKeys("a")
//                .keyUp(Keys.CONTROL)
//                .perform();


        dateOfBirth.click();
      //  dateOfBirth.clear();
//        dateOfBirth.sendKeys(Keys.CONTROL,"a");
        dateOfBirth.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        dateOfBirth.sendKeys("12 Dec 1988");
        dateOfBirth.sendKeys(Keys.ENTER);
      //  Thread.sleep(1600);

        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        subjects.sendKeys("Maths");
        subjects.sendKeys(Keys.ENTER);
       // Thread.sleep(1600);

        subjects.sendKeys("Chemis");
        subjects.sendKeys(Keys.ENTER);

//                new Actions(driver)
//                .scrollToElement(   driver.findElement(By
//                        .xpath("//label[contains(@for,'hobbies-checkbox-1')][normalize-space()='Sport']")))
//                .perform();


        driver.findElement(By
                .xpath("//label[normalize-space()='Sports']")).click();

        driver.findElement(By
                .xpath("//label[normalize-space()='Music']")).click();


//        driver.findElement(By
//                        .xpath("//label[contains(@for,'hobbies-checkbox-1')][normalize-space()='Sport']"))
//                .click();
//        driver.findElement(By
//                        .xpath("//label[contains(@for,'hobbies-checkbox-3')][normalize-space()='Music']"))
//                .click();
        //C:\Users\leonardo.gloria\Desktop\teste.txt
        Path path = Paths.get("C:\\Users\\leonardo.gloria\\Desktop\\teste.txt");
        driver.findElement(By.id("uploadPicture")).sendKeys(path.toString());
        Thread.sleep(1600);

        WebElement element = driver.findElement(By.id("currentAddress"));
        new Actions(driver)
                .scrollToElement(element)
                .sendKeys("Rua Teeste, 42 Terra")
                .perform();

        WebElement state = driver.findElement(By.id("react-select-3-input"));
        state.sendKeys("NCR");
        state.sendKeys(Keys.ENTER);
        Thread.sleep(1600);

        WebElement city = driver.findElement(By.id("react-select-4-input"));
        city.sendKeys("Delhi");
        city.sendKeys(Keys.ENTER);

    }

}
