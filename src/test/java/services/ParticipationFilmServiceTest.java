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







}
