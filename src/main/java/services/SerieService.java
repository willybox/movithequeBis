package services;

import entities.ActeurEntity;
import entities.SerieEntity;
import entities.SerieEntity;
import enumerations.ImportanceEnum;
import exceptions.SerieDejaExistante;
import repositories.SerieRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.SerieRepository;

import java.util.List;

@Service
public class SerieService {

    public SerieRepository serieRepository;
    public ParticipationSerieService participationSerieService;

    @Autowired
    public SerieService(SerieRepository serieRepository, ParticipationSerieService participationSerieService){
        this.serieRepository=serieRepository;
        this.participationSerieService = participationSerieService;
    }

    @Transactional
    public SerieEntity createSerie(SerieEntity serieEntity,
                                  List<Long> listeActeur1,
                                  List<Long> listeActeur2,
                                  List<Long> listeActeur3) {

        save(serieEntity);
        participationSerieService.addParticipationsSerie(listeActeur1, serieEntity, ImportanceEnum.IMPORTANCE_PRINCIPALE);
        participationSerieService.addParticipationsSerie(listeActeur2, serieEntity, ImportanceEnum.IMPORTANCE_SECONDAIRE);
        participationSerieService.addParticipationsSerie(listeActeur3, serieEntity, ImportanceEnum.IMPORTANCE_INCONNUE);
        return serieEntity;
    }

    @Transactional(readOnly = true)
    public List<SerieEntity> getSeries() {
        return serieRepository.findAll();
    }

    @Transactional
    public void deleteSerie(Long serieId) {
        participationSerieService.deleteParticipationSerieFromSerie(serieId);
        serieRepository.delete(serieId);
    }

    @Transactional
    public SerieEntity modifierSerie(Long serieId,
                                   SerieEntity serieEntity,
                                   List<Long> listeActeurId1,
                                   List<Long> listeActeurId2,
                                   List<Long> listeActeurId3){

        SerieEntity update = serieRepository.findOne(serieId);
        update.setNom(serieEntity.getNom());
        update.setDateDeSortie(serieEntity.getDateDeSortie());

        participationSerieService.deleteParticipationSerieFromSerie(serieId);

        participationSerieService.addParticipationsSerie(listeActeurId1, update, ImportanceEnum.IMPORTANCE_PRINCIPALE);
        participationSerieService.addParticipationsSerie(listeActeurId2, update, ImportanceEnum.IMPORTANCE_SECONDAIRE);
        participationSerieService.addParticipationsSerie(listeActeurId3, update, ImportanceEnum.IMPORTANCE_INCONNUE);

        save(update);
        return update;
    }

    @Transactional
    private void save(SerieEntity serieEntity) {
        try{
            serieRepository.save(serieEntity);
        }
        catch(DataIntegrityViolationException ex){
            Throwable t = ex.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if(t instanceof ConstraintViolationException){
                if( ex.getMostSpecificCause().getMessage().contains("Duplicate entry"))
                    throw new SerieDejaExistante();
            }
        }
    }

    @Transactional(readOnly = true)
    public SerieEntity getSerie(Long serieId) {
        return serieRepository.findById(serieId);
    }
}
