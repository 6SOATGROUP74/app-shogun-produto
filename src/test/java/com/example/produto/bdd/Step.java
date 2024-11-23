package com.example.produto.bdd;

import com.example.produto.ProdutoCommon;
import com.example.produto.adapter.controller.response.ProdutoResponse;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Step {

    private Response response;
    private ProdutoResponse produtoReponse;
    private final String ENDPOINT_PRODUTOS = "http://localhost:8080/v1/produtos";


    @Quando("submeter o registro de um novo produto")
    public ProdutoResponse submeterNovoProduto() {
        final var produtoRequest = ProdutoCommon.factoryProdutoRequest();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(produtoRequest)
                .when().post(ENDPOINT_PRODUTOS);
        return response.then().extract().as(ProdutoResponse.class);
    }

    @Então("o produto deve ser registrado com sucesso")
    public void mensagemRegistradaComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
