package services;

import entities.*;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActeurRepository;
import enumerations.ImportanceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ParticipationSerieRepository;

import java.util.List;

@Service
public class ParticipationSerieService {

    public ParticipationSerieRepository participationSerieRepository;

    @Autowired
    public ParticipationSerieService(ParticipationSerieService participationSerieRepositor){
        this.participationSerieRepository=participationSerieRepository;
    }

    @Transactional
    public void ajouterParticipationSerie(ActeurEntity acteur, Long serieId, ImportanceEnum importance){
        participationSerieRepository.enregistrerParticipation(acteur.getId(),serieId,importance.toString());
    }

    public void ajouterParticipationSerie(ActeurEntity acteur, Long serieId){
        this.ajouterParticipationSerie(acteur,serieId, ImportanceEnum.IMPORTANCE_INCONNUE);
    }

    @Transactional
    public void ajouterParticipationActeur(SerieEntity serie, Long acteurId, ImportanceEnum importance){
        participationSerieRepository.enregistrerParticipation(acteurId,serie.getId(),importance.toString());
    }

    public void ajouterParticipationActeur(SerieEntity serie, Long acteurId){
        this.ajouterParticipationActeur(serie,acteurId, ImportanceEnum.IMPORTANCE_INCONNUE);
    }

    public ParticipationSerieEntity getParticipationSerie(Long acteur, Long serie){
        return participationSerieRepository.search(acteur,serie);
    }

    public void deleteParticipationSerieFromActeur(Long acteurId){
        participationSerieRepository.deleteByActeur(acteurId);
    }

    public void deleteParticipationSerieFromSerie(Long serieId){
        participationSerieRepository.deleteBySerie(serieId);
    }

    public void addParticipationsActeur(List<Long> listeSerieId, ActeurEntity acteur, ImportanceEnum importance) {
        for(Long serieId : listeSerieId){
            this.ajouterParticipationSerie(acteur,serieId, importance);
        }
    }

    public void addParticipationsSerie(List<Long> listeActeurId, SerieEntity serie, ImportanceEnum importance) {
        for(Long acteurId : listeActeurId){
            this.ajouterParticipationActeur(serie,acteurId, importance);
        }
    }

}
