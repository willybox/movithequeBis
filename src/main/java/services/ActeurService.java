package services;

import entities.ActeurEntity;
import entities.ActeurRepository;
import exceptions.ActeurDejaExistant;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActeurService {
    public ActeurRepository acteurRepository;
    public ParticipationFilmService participationFilmService;

    @Autowired
    public ActeurService(ActeurRepository acteurRepository, ParticipationFilmService participationFilmService){
        this.acteurRepository=acteurRepository;
        this.participationFilmService=participationFilmService;
    }

    @Transactional
    public ActeurEntity createActeur(ActeurEntity acteurEntity) {
        save(acteurEntity);
        return acteurEntity;
    }

    @Transactional(readOnly = true)
    public List<ActeurEntity> getActeurs() {
        return acteurRepository.findAll();
    }

    @Transactional
    public void deleteActeur(Long acteurId) {
        participationFilmService.deleteParticipationFilm(acteurId);
        acteurRepository.delete(acteurId);
    }

    public ActeurEntity modifierActeur(Long acteurId, ActeurEntity acteurEntity) {
        ActeurEntity update = acteurRepository.findOne(acteurId);
        update.setNom(acteurEntity.getNom());
        update.setPrenom(acteurEntity.getPrenom());
        update.setDateDeNaissance(acteurEntity.getDateDeNaissance());
        save(update);
        return update;
    }

    private void save(ActeurEntity acteurEntity) {
        try{
            acteurRepository.save(acteurEntity);
        }
        catch(DataIntegrityViolationException ex){
            Throwable t = ex.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if(t instanceof ConstraintViolationException){
                if( ex.getMostSpecificCause().getMessage().contains("Duplicate entry"))
                    throw new ActeurDejaExistant();
            }
        }
    }

    @Transactional(readOnly = true)
    public ActeurEntity getActeur(Long acteurId) {
        return acteurRepository.findById(acteurId);
    }
}
