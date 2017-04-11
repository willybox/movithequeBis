package services;

import entities.FilmEntity;
import entities.FilmRepository;
import exceptions.FilmDejaExistant;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FilmService {

    public FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository){
        this.filmRepository=filmRepository;
    }

    @Transactional
    public FilmEntity createFilm(FilmEntity filmEntity) throws FilmDejaExistant {
        save(filmEntity);
        return filmEntity;
    }

    @Transactional(readOnly = true)
    public List<FilmEntity> getFilms() {
        return filmRepository.findAll();
    }

    @Transactional
    public void deleteFilm(Long filmId) {
        filmRepository.delete(filmId);
    }

    public FilmEntity modifierFilm(Long filmId, FilmEntity filmEntity) throws FilmDejaExistant {
        FilmEntity update = filmRepository.findOne(filmId);
        update.setName(filmEntity.getName());
        update.setParticipationFilmList(filmEntity.getParticipationFilmList());
        save(update);
        return update;
    }

    private void save(FilmEntity filmEntity) throws FilmDejaExistant {
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

}
