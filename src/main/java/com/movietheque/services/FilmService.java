package com.movietheque.services;

import com.movietheque.entities.FilmEntity;
import com.movietheque.enumerations.ImportanceEnum;
import com.movietheque.repositories.FilmRepository;
import com.movietheque.exceptions.FilmDejaExistant;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmService {

    public FilmRepository filmRepository;
    public ParticipationFilmService participationFilmService;

    @Autowired
    public FilmService(FilmRepository filmRepository, ParticipationFilmService participationFilmService){
        this.filmRepository=filmRepository;
        this.participationFilmService = participationFilmService;
    }

    @Transactional
    public FilmEntity createFilm(FilmEntity filmEntity,
                                 List<Long> listeActeur1,
                                 List<Long> listeActeur2,
                                 List<Long> listeActeur3) {

        save(filmEntity);
        participationFilmService.addParticipationsFilm(listeActeur1, filmEntity, ImportanceEnum.IMPORTANCE_PRINCIPALE);
        participationFilmService.addParticipationsFilm(listeActeur2, filmEntity, ImportanceEnum.IMPORTANCE_SECONDAIRE);
        participationFilmService.addParticipationsFilm(listeActeur3, filmEntity, ImportanceEnum.IMPORTANCE_INCONNUE);
        return filmEntity;
    }

    @Transactional(readOnly = true)
    public List<FilmEntity> getFilms() {
        return filmRepository.findAll();
    }

    @Transactional
    public void deleteFilm(Long filmId) {
        participationFilmService.deleteParticipationFilmFromFilm(filmId);
        filmRepository.delete(filmId);
    }

    @Transactional
    public FilmEntity modifierFilm(Long filmId,
                                   FilmEntity filmEntity,
                                   List<Long> listeActeurId1,
                                   List<Long> listeActeurId2,
                                   List<Long> listeActeurId3){

        FilmEntity update = filmRepository.findOne(filmId);
        update.setNom(filmEntity.getNom());
        update.setDateDeSortie(filmEntity.getDateDeSortie());

        participationFilmService.deleteParticipationFilmFromFilm(filmId);

        participationFilmService.addParticipationsFilm(listeActeurId1, update, ImportanceEnum.IMPORTANCE_PRINCIPALE);
        participationFilmService.addParticipationsFilm(listeActeurId2, update, ImportanceEnum.IMPORTANCE_SECONDAIRE);
        participationFilmService.addParticipationsFilm(listeActeurId3, update, ImportanceEnum.IMPORTANCE_INCONNUE);

        save(update);
        return update;
    }

    @Transactional
    private void save(FilmEntity filmEntity) {
        try{
            filmRepository.save(filmEntity);
        }
        catch(DataIntegrityViolationException ex){
            Throwable t = ex.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if(t instanceof ConstraintViolationException){
                if( ex.getMostSpecificCause().getMessage().contains("Duplicate entry"))
                    throw new FilmDejaExistant();
            }
        }
    }

    @Transactional(readOnly = true)
    public FilmEntity getFilm(Long filmId) {
        return filmRepository.findById(filmId);
    }
}
