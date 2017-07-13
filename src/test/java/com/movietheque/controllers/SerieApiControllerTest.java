package com.movietheque.controllers;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class SerieApiControllerTest {

    @LocalServerPort
    private int localServerPort;

    @Before
    public void init(){
        RestAssured.port = localServerPort;
    }

    @Test
    @DirtiesContext
    public void add_nouvelle_serie_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","breaking bad")
        .when()
            .post("api/serie/add")
        .then()
            .statusCode(201)
            .body("id",is(1))
            .body("nom",is("breaking bad"));
    }

    @Test
    @DirtiesContext
    public void add_nouvelle_serie_avec_date_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","breaking bad")
            .formParam("dateDeSortie","2000-01-01")
        .when()
            .post("api/serie/add")
        .then()
            .statusCode(201)
            .body("id",is(1))
            .body("nom",is("breaking bad"));
    }

    @Test
    @DirtiesContext
    public void add_nouvelle_serie_avec_liste_should_succeed() {

        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","a")
            .formParam("prenom","a")
        .when()
            .post("api/acteur/add");

        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","qwerty")
            .formParam("selectActeur1","[\"1\"]")
        .when()
            .post("api/serie/add")
        .then()
            .statusCode(201)
            .body("id",is(1))
            .body("nom",is("qwerty"));
    }

    @Test
    @DirtiesContext
    public void add_nouvelle_serie_avec_acteur_inconnu_should_fail() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","a")
            .formParam("selectActeur1","[\"1\"]")
        .when()
            .post("api/serie/add")
        .then()
            .statusCode(500);
    }

    @Test
    @DirtiesContext
    public void add_nouvelle_serie_sans_nom_should_fail() {
        given()
            .log().all()
            .formParam("id","1")
        .when()
            .post("api/serie/add")
        .then()
            .statusCode(400);
    }

    @Test
    public void remove_serie_not_exists_should_500() {
        given()
            .log().all()
            .formParam("id","1")
        .when()
            .post("api/serie/delete/1")
        .then()
            .statusCode(500);
    }

    @Test
    @DirtiesContext
    public void remove_serie_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","a")
        .when()
            .post("api/serie/add");

        given()
            .log().all()
        .when()
            .post("api/serie/delete/1")
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    @DirtiesContext
    public void get_serie_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","a")
        .when()
            .post("api/serie/add");

        given()
            .log().all()
        .when()
            .get("api/serie/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("id",is(1))
            .body("nom",is("a"));
    }

    @Test
    @DirtiesContext
    public void update_serie_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","a")
        .when()
            .post("api/serie/add");

        given()
            .log().all()
                .formParam("nom","aw")
        .when()
            .put("api/serie/update/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("nom",is("aw"));
    }

    @Test
    @DirtiesContext
    public void should_get_all_series(){
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","a")
        .when()
            .post("api/serie/add");
        given()
            .log().all()
            .formParam("id","2")
            .formParam("nom","b")
        .when()
            .post("api/serie/add");
        given()
            .log().all()
            .formParam("id","3")
            .formParam("nom","c")
        .when()
            .post("api/serie/add");
        given()
            .log().all()
        .when()
            .get("/api/serie/")
        .then()
            .log().all()
            .statusCode(200)
            .body("$",hasSize(3));
    }

}
