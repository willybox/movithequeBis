package services;

import entities.ActeurEntity;
import entities.FilmEntity;
import entities.ParticipationFilmEntity;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActeurRepository;
import repositories.FilmRepository;
import repositories.ParticipationFilmRepository;
import enumerations.ImportanceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationFilmService {

    public ParticipationFilmRepository participationFilmRepository;

    @Autowired
    public ParticipationFilmService(ParticipationFilmRepository participationFilmRepository, FilmRepository filmRepository, ActeurRepository acteurRepository){
        this.participationFilmRepository=participationFilmRepository;
    }

    public int nombreFilm(ActeurEntity acteur) {
            return acteur.getParticipationFilmList().size();
    }

    public int nombreActeur(FilmEntity film) {
        return film.getParticipationFilmList().size();
    }

    @Transactional
    public void ajouterParticipationFilm(ActeurEntity acteur, Long filmId, ImportanceEnum importance){
        participationFilmRepository.enregistrerParticipation(acteur.getId(),filmId,importance.toString());
    }

    public void ajouterParticipationFilm(ActeurEntity acteur, Long filmId){
        this.ajouterParticipationFilm(acteur,filmId, ImportanceEnum.IMPORTANCE_INCONNUE);
    }

    @Transactional
    public void ajouterParticipationActeur(FilmEntity film, Long acteurId, ImportanceEnum importance){
        participationFilmRepository.enregistrerParticipation(acteurId,film.getId(),importance.toString());
    }

    public void ajouterParticipationActeur(FilmEntity film, Long acteurId){
        this.ajouterParticipationActeur(film,acteurId, ImportanceEnum.IMPORTANCE_INCONNUE);
    }

    public ParticipationFilmEntity getParticipationFilm(Long acteur, Long film){
        return participationFilmRepository.search(acteur,film);
    }

    public void deleteParticipationFilmFromActeur(Long acteurId){
        participationFilmRepository.deleteByActeur(acteurId);
    }

    public void deleteParticipationFilmFromFilm(Long filmId){
        participationFilmRepository.deleteByFilm(filmId);
    }

    public void addParticipationsActeur(List<Long> listeFilmId, ActeurEntity acteur, ImportanceEnum importance) {
        for(Long filmId : listeFilmId){
            this.ajouterParticipationFilm(acteur,filmId, importance);
        }
    }

    public void addParticipationsFilm(List<Long> listeActeurId, FilmEntity film, ImportanceEnum importance) {
        for(Long acteurId : listeActeurId){
            this.ajouterParticipationActeur(film,acteurId, importance);
        }
    }

}
