// 1 - Pacote
package petstore;

// 2- Bibliotecas

import io.restassured.specification.Argument;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

// 3- Classe
public class Pet {
    // 3.1 - Atributos (campos, informações)
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    // 3.2 - Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - Post
    @Test // Identifica o método ou função como teste para o TesteNG (jUnit tbm)
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given() // Dado
                .contentType("application/json") // comum em API REST - antigas era "text/xml"
                .log().all()
                .body(jsonBody)

                .when() // When
                .post(uri)

                .then() // Then
                .log().all()
                .statusCode(200)
                .body("name", is("Pitucha"))
                .body("status", is("available"))
        ;
    }
}
