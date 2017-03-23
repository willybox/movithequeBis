package com;


import entities.ActeurEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import services.ActorService;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ActorServiceTest {

    @InjectMocks
    ActorService actorService;

    ActeurEntity actor;

    @Test
    public void get_number_of_movie_of_new_actor_should_be_0(){
        actor = new ActeurEntity();

        int nbMovies = actorService.getNumberOfMovie(actor);

        assertThat(nbMovies).isEqualTo(0);
    }

//    @Test
//    public void add_movie_should_add_movie_to_the_list(){
//        actor = new ActeurEntity();
//        FilmEntity movie = new FilmEntity();
//
//        actorService.addMovie(actor,movie);
//
//        assertThat(actor.getActorMovieList().size()).isEqualTo(1);
//
//    }



}
