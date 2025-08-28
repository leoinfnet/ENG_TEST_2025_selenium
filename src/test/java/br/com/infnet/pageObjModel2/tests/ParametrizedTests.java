package br.com.infnet.pageObjModel2.tests;

import br.com.infnet.BaseTest;
import br.com.infnet.pageObjModel2.pages.LoginPage;
import br.com.infnet.pageObjModel2.pages.SecureAreaPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ParametrizedTests extends BaseTest {
    @ParameterizedTest(name= "[{index}] Usuario={0}, Senha={1}, EsperaSucesso={2}")
    @CsvSource({
            "tomsmith,SuperSecretPassword!,true",
            "tomsmith2,SuperSecretPassword!,false",
            "tomsmith,SuperSecretPassword,false"
    })
    void deveLogarComSucesso(String usuario,String senha, boolean sucesso){
        LoginPage login = new LoginPage(driver)
                .abrir()
                .preencherUsuario(usuario)
                .preencherSenha(senha);

        if(sucesso){
            SecureAreaPage secure = login.logarComSucesso();
            assertThat(secure.titulo()).containsIgnoringCase("secure area");
            assertThat(secure.mensagem()).containsIgnoringCase("You logged into a secure area!");

        }else{
            assertThat( login
                    .submterEsperandoError()
                    .mensagem())
                    .containsIgnoringCase("Your password is invalid!");
        }
    }
}
