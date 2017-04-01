package services;

import entities.ActeurEntity;
import entities.ActeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActeurService {
    public ActeurRepository acteurRepository;

    @Autowired
    public ActeurService(ActeurRepository acteurRepository){
        this.acteurRepository=acteurRepository;
    }

    @Transactional
    public ActeurEntity createActeur(ActeurEntity acteurEntity) {
        acteurRepository.save(acteurEntity);
        return acteurEntity;
    }

    @Transactional(readOnly = true)
    public List<ActeurEntity> getActeurs() {
        return acteurRepository.findAll();
    }

    @Transactional
    public void deleteActeur(Long acteurId) {
        acteurRepository.delete(acteurId);
    }

    public ActeurEntity modifierActeur(Long acteurId, ActeurEntity acteurEntity) {
        ActeurEntity update = acteurRepository.findOne(acteurId);
        update.setNom(acteurEntity.getNom());
        update.setPrenom(acteurEntity.getPrenom());
        acteurRepository.save(update);
        return update;
    }
}
