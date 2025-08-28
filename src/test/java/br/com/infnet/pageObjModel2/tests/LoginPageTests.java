package br.com.infnet.pageObjModel2.tests;

import br.com.infnet.BaseTest;
import br.com.infnet.pageObjModel2.pages.LoginPage;
import br.com.infnet.pageObjModel2.pages.SecureAreaPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTests extends BaseTest {
    @Test
    void deveLogarComSucesso(){
       SecureAreaPage secure =new LoginPage(driver)
                .abrir()
                .preencherUsuario("tomsmith")
                .preencherSenha("SuperSecretPassword!")
                .logarComSucesso();

       assertThat(secure.titulo()).containsIgnoringCase("secure area");
       assertThat(secure.mensagem()).containsIgnoringCase("You logged into a secure area!");
    }
    @Test
    void naoDeveLogar(){
        LoginPage page = new LoginPage(driver)
                .abrir()
                .preencherUsuario("tomsmith")
                .preencherSenha("errada!")
                        .submterEsperandoError();
        assertThat(page.mensagem()).containsIgnoringCase("Your password is invalid!");
    }
}
