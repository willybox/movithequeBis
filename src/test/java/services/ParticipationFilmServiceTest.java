package services;


import entities.ActeurEntity;
import entities.FilmEntity;
import entities.ParticipationFilmEntity;
import enumerations.ImportanceEnum;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import exceptions.ParticipationDejaExistante;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ParticipationFilmServiceTest {

    @Autowired
    ParticipationFilmService participationFilmService;

    ActeurEntity acteur;
    FilmEntity film;

    @Before
    public void init(){
       // participationFilmService = new ParticipationFilmService();
    }


    @Test
    public void le_nombre_de_film_d_un_nouvel_acteur_doit_etre_de_0(){
        acteur = new ActeurEntity();

        int nbFilm = participationFilmService.nombreFilm(acteur);

        assertThat(nbFilm).isEqualTo(0);
    }

    @Test
    public void le_nombre_d_acteur_d_un_nouveau_film_doit_etre_de_0(){
        film = new FilmEntity();

        int nbActeur = participationFilmService.nombreActeur(film);

        assertThat(nbActeur).isEqualTo(0);
    }






}
