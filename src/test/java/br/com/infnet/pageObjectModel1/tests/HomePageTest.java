package br.com.infnet.pageObjectModel1.tests;

import br.com.infnet.pageObjectModel1.core.BaseTest2;
import br.com.infnet.pageObjectModel1.pages.HomePage;
import br.com.infnet.pageObjectModel1.pages.ResultadoBuscaPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTest extends BaseTest2 {
    @Test
    void deveBuscarARtigosDoEleniumNaWiki(){
        HomePage home = new HomePage(driver).abrir();
        ResultadoBuscaPage resultado = home.buscarPor("Selenium (software)");
        assertThat(resultado.lerTituloPrincipal()).
                containsIgnoringCase("Selenium");
        assertThat(resultado.getTitle()).contains("Selenium");


    }
}
