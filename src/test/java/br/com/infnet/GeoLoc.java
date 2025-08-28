package br.com.infnet;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoLoc extends BaseTest{
    @Test
    public void deveTEstarGeoLocComCDP() throws InterruptedException {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();

        Map<String, Object> grant = Map.of(
                "origin", "https://the-internet.herokuapp.com/geolocation",
                "permissions", List.of("geolocation")
        );
        ((ChromeDriver) driver).executeCdpCommand("Browser.grantPermissions", grant);

        Map<String,Object> coords = Map.of(
                "latitude", -23.5505,
                "longitude", -46.6333,
                "accuracy", 50
        );
        ((ChromeDriver) driver).executeCdpCommand("Emulation.setGeolocationOverride",coords);

        driver.get("https://the-internet.herokuapp.com/geolocation");
        driver.findElement(By.xpath("//button[text()='Where am I?']")).click();
        Thread.sleep(2000);

        String latEsperada = driver.findElement(By.id("lat-value")).getText();
        String lgnEsperada = driver.findElement(By.id("long-value")).getText();

        assertEquals(Double.parseDouble(latEsperada), (Double)(coords.get("latitude")) ,0.0001);
        assertEquals(Double.parseDouble(lgnEsperada), (Double)(coords.get("longitude")) ,0.0001);

    }
}
