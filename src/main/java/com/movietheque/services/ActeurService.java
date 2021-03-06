package com.movietheque.services;

import com.movietheque.entities.ActeurEntity;
import com.movietheque.enumerations.ImportanceEnum;
import com.movietheque.repositories.ActeurRepository;
import com.movietheque.exceptions.ActeurDejaExistant;
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
    public ActeurEntity createActeur(ActeurEntity acteurEntity,
                                     List<Long> listeFilmId1,
                                     List<Long> listeFilmId2,
                                     List<Long> listeFilmId3) {
        save(acteurEntity);

        participationFilmService.addParticipationsActeur(listeFilmId1, acteurEntity, ImportanceEnum.IMPORTANCE_PRINCIPALE);
        participationFilmService.addParticipationsActeur(listeFilmId2, acteurEntity, ImportanceEnum.IMPORTANCE_SECONDAIRE);
        participationFilmService.addParticipationsActeur(listeFilmId3, acteurEntity, ImportanceEnum.IMPORTANCE_INCONNUE);
        return acteurEntity;
    }

    @Transactional(readOnly = true)
    public List<ActeurEntity> getActeurs() {
        return acteurRepository.findAll();
    }

    @Transactional
    public void deleteActeur(Long acteurId) {
        participationFilmService.deleteParticipationFilmFromActeur(acteurId);
        acteurRepository.delete(acteurId);
    }

    @Transactional
    public ActeurEntity modifierActeur(Long acteurId,
                                       ActeurEntity acteurEntity,
                                       List<Long> listeFilmId1,
                                       List<Long> listeFilmId2,
                                       List<Long> listeFilmId3) {
        ActeurEntity update = acteurRepository.findOne(acteurId);
        update.setNom(acteurEntity.getNom());
        update.setPrenom(acteurEntity.getPrenom());
        update.setDateDeNaissance(acteurEntity.getDateDeNaissance());
        update.setId(acteurId);
        participationFilmService.deleteParticipationFilmFromActeur(acteurId);

        participationFilmService.addParticipationsActeur(listeFilmId1, update, ImportanceEnum.IMPORTANCE_PRINCIPALE);
        participationFilmService.addParticipationsActeur(listeFilmId2, update, ImportanceEnum.IMPORTANCE_SECONDAIRE);
        participationFilmService.addParticipationsActeur(listeFilmId3, update, ImportanceEnum.IMPORTANCE_INCONNUE);

        save(update);
        return update;
    }

    @Transactional
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
