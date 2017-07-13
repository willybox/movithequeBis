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
public class FilmApiControllerTest {
    @LocalServerPort
    private int localServerPort;

    @Before
    public void init(){
        RestAssured.port = localServerPort;
    }

    @Test
    @DirtiesContext
    public void add_new_movie_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","testFilmAdd")
        .when()
            .post("api/film/add")
        .then()
            .statusCode(201)
            .body("id",is(1))
            .body("nom",is("testFilmAdd"));
    }

    @Test
    @DirtiesContext
    public void add_new_movie_without_name_should_fail() {
        given()
            .log().all()
            .formParam("id","1")
        .when()
            .post("api/film/add")
        .then()
            .statusCode(400);
    }

    @Test
    public void remove_movie_not_exists_should_404() {
        given()
            .log().all()
            .formParam("id","1")
        .when()
            .post("api/delete/1")
        .then()
            .statusCode(404);
    }

    @Test
    @DirtiesContext
    public void remove_movie_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","testFilmRemove")
        .when()
            .post("api/film/add");

        given()
            .log().all()
        .when()
            .post("api/film/delete/1")
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    @DirtiesContext
    public void get_movie_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","testFilmGet")
        .when()
            .post("api/film/add");

        given()
            .log().all()
        .when()
            .get("api/film/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("id",is(1))
            .body("nom",is("testFilmGet"));
    }

    @Test
    @DirtiesContext
    public void update_movie_should_succeed() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","testFilmUpdate")
        .when()
            .post("api/film/add");

        given()
            .log().all()
            .formParam("nom","testUpdateNomFilm")
        .when()
            .put("api/film/update/1")
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    @DirtiesContext
    public void should_get_all_accounts(){
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","Film1")
        .when()
            .post("api/film/add");

        given()
            .log().all()
            .formParam("id","2")
            .formParam("nom","Film2")
        .when()
            .post("api/film/add");

        given()
            .log().all()
            .formParam("id","3")
            .formParam("nom","Film3")
        .when()
            .post("api/film/add");

        given()
            .log().all()
        .when()
            .get("/api/film/")
        .then()
            .log().all()
            .statusCode(200)
            .body("$",hasSize(3));
    }

    @Test
    @DirtiesContext
    public void add_new_movie_with_actors_should_succeed() {

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

        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","FilmAvecActeurs")
            .formParam("selectActeur1","[\"1\"]")
        .when()
            .post("api/film/add");
    }

    @Test
    @DirtiesContext
    public void add_new_movie_with_unkonw_actor_should_fail() {
        given()
            .log().all()
            .formParam("id","1")
            .formParam("nom","FilmActeurInconnu")
            .formParam("selectActeur1","[\"1\"]")
        .when()
            .post("api/film/add")
        .then()
            .statusCode(500);
    }
}
