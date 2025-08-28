package br.com.infnet;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CookiesTest extends BaseTest {
    @Test
    void deveCriarLerExcluirCookies(){
        driver.get("https://example.org");
        Set<Cookie> cookies =
                driver.manage().getCookies();
        System.out.println("Cookies Iniciais " + cookies );

        Cookie authToken = new Cookie.Builder("auth_token", "123456")
                .domain("example.org")
                .path("/")
                .isHttpOnly(true)
                .isSecure(false)
                .build();
        driver.manage().addCookie(authToken);
        Cookie lido = driver.manage().getCookieNamed("auth_token");
        assertNotNull(lido);
        driver.manage().deleteCookie(lido);

        assertNull( driver.manage().getCookieNamed("auth_token"));

        driver.manage().deleteAllCookies();
        assertTrue(driver.manage().getCookies().isEmpty());

    }
}
