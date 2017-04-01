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
    public ActeurService(ActeurRepository accountRepository){
        this.acteurRepository=accountRepository;
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
}
