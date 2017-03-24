package services;

import entities.ActeurEntity;
import entities.FilmEntity;
import entities.ParticipationFilmEntity;
import enumerations.ImportanceEnum;
import exceptions.ParticipationDejaExistante;
import org.springframework.stereotype.Service;

@Service
public class ParticipationFilmService {

    public int nombreFilm(ActeurEntity acteur) {
        return acteur.getParticipationFilmList().size();
    }

    public int nombreActeur(FilmEntity film) {
        return film.getParticipationFilmList().size();
    }

    public ParticipationFilmEntity ajouterParticipationFilm(ActeurEntity acteur, FilmEntity film, ImportanceEnum importance){
        for(ParticipationFilmEntity participationFilmEntity1 : acteur.getParticipationFilmList() ){
            if(participationFilmEntity1.getActeur() == acteur && participationFilmEntity1.getFilm()== film){
                throw new ParticipationDejaExistante();
            }
        }

        ParticipationFilmEntity participationFilmEntity = ParticipationFilmEntity.builder().film(film).acteur(acteur).importance(importance).build();

        acteur.getParticipationFilmList().add(participationFilmEntity);
        film.getParticipationFilmList().add(participationFilmEntity);

        return participationFilmEntity;
    }

    public ParticipationFilmEntity ajouterParticipationFilm(ActeurEntity acteur, FilmEntity film){
        return this.ajouterParticipationFilm(acteur,film, ImportanceEnum.IMPORTANCE_INCONNUE);
    }




}
