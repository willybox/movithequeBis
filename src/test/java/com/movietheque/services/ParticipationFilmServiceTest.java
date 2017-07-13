package com.movietheque.services;


import com.movietheque.entities.ActeurEntity;
import com.movietheque.entities.FilmEntity;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

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
