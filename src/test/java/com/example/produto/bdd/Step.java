package com.example.produto.bdd;

import com.example.produto.ProdutoCommon;
import com.example.produto.adapter.controller.response.ProdutoResponse;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static io.restassured.RestAssured.given;

public class Step {

    private Response response;
    private ProdutoResponse produtoReponse;
    private final String ENDPOINT_PRODUTOS = "http://abdba3bf7d28e41d880e9539e9988589-234110984.us-east-1.elb.amazonaws.com";


    @Quando("submeter o registro de um novo produto")
    public ProdutoResponse submeterNovoProduto() {
        final var produtoRequest = ProdutoCommon.factoryProdutoRequest();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(produtoRequest)
                .when().post(ENDPOINT_PRODUTOS.concat("/v1/produtos"));
        return response.then().extract().as(ProdutoResponse.class);
    }

    @Então("o produto deve ser registrado com sucesso")
    public void mensagemRegistradaComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Quando("submeter uma consulta por uma categoria inexistente ou invalida")
    public void submeterConsultaCategoriaInvalidaOuInexistente() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get(ENDPOINT_PRODUTOS.concat("/v1/categoria/DASDADASD/produto"));

    }

    @Então("deve apresentar o response code  400")
    public void deveApresentarResponseCode400() {
        response.then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
