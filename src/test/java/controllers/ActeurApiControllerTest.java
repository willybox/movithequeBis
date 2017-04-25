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
        given()
                .log().all()
                .formParam("id","1")
                .formParam("nom","a")
                .formParam("prenom","a")
        .when()
                .post("api/acteur/add")
        .then()
                .statusCode(201)
                .body("id",is(1))
                .body("nom",is("a"))
                .body("prenom",is("a"));
    }

    @Test
    public void remove_acteur_not_exists_should_404() {
        given()
            .log().all()
            .formParam("id","1")
        .when()
            .post("api/delete/1")
        .then()
            .statusCode(404);
    }

    @Test
    public void remove_acteur_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","a")
            .formParam("prenom","a")
        .when()
            .post("api/acteur/add");

        given()
            .log().all()
        .when()
            .post("api/acteur/delete/1")
        .then()
            .log().all()
            .statusCode(200);
    }

}