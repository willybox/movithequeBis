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
        participationFilmService = new ParticipationFilmService();
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

    @Test
    public void le_nombre_de_film_apres_l_ajout_d_une_nouvelle_participation_doit_etre_de_1(){
        acteur = new ActeurEntity();
        film = new FilmEntity();
        participationFilmService.ajouterParticipationFilm(acteur,film);

        int nbFilm = participationFilmService.nombreFilm(acteur);

        assertThat(nbFilm).isEqualTo(1);
    }

    @Test
    public void le_nombre_d_acteur_apres_l_ajout_d_une_nouvelle_participation_doit_etre_de_1(){
        acteur = new ActeurEntity();
        film = new FilmEntity();
        participationFilmService.ajouterParticipationFilm(acteur,film);

        int nbActeur = participationFilmService.nombreActeur(film);

        assertThat(nbActeur).isEqualTo(1);
    }

    @Test(expected= ParticipationDejaExistante.class)
    public void ajouter_deux_fois_la_meme_participation_doit_renvoyer_une_exception_participation_deja_existante(){
        acteur = new ActeurEntity();
        film = new FilmEntity();

        participationFilmService.ajouterParticipationFilm(acteur,film);
        participationFilmService.ajouterParticipationFilm(acteur,film);
    }

    @Test
    public void ajouter_participation_sans_importance_doit_creer_une_participation_avec_importance_inconnue(){
        acteur = new ActeurEntity();
        film = new FilmEntity();

        ParticipationFilmEntity participationFilmEntity = participationFilmService.ajouterParticipationFilm(acteur,film);

        assertThat(participationFilmEntity.getImportance()).isEqualTo(ImportanceEnum.IMPORTANCE_INCONNUE);
    }





}
