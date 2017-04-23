package services;

import entities.FilmEntity;
import repositories.FilmRepository;
import exceptions.FilmDejaExistant;
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
    public FilmEntity createFilm(FilmEntity filmEntity) {
        save(filmEntity);
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

    public FilmEntity modifierFilm(Long filmId, FilmEntity filmEntity){
        FilmEntity update = filmRepository.findOne(filmId);
        update.setNom(filmEntity.getNom());
        update.setDateDeSortie(filmEntity.getDateDeSortie());
        save(update);
        return update;
    }

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
