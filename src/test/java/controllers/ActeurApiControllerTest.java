package controllers;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import entities.ActeurEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ActeurApiControllerTest {

    @LocalServerPort
    private int localServerPort;

    @Before
    public void init(){
        RestAssured.port = localServerPort;
    }

    @Test
    public void add_nouvel_acteur_should_succeed() {
        ActeurEntity acteur = ActeurEntity.builder()
                .id(5L)
                .nom("a")
                .prenom("a")
                .build();
        given()
                .log().all()
                .contentType(JSON)
                .body(acteur)
        .when()
                .post("api/acteur/add")
        .then()
                .log().all()
                .statusCode(201)
                .body("id",is(5L))
                .body("nom",is("a"))
                .body("prenom",is("a"));
    }

}
