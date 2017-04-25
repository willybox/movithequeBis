package repositories;


import entities.ParticipationFilmEntity;
import entities.ParticipationSerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationSerieRepository extends JpaRepository<ParticipationSerieEntity, Long> {

    @Modifying
    @Query(nativeQuery = true, value="delete from participation_serie where acteur_id=:acteur_id")
    void deleteByActeur(@Param("acteur_id") Long acteurId);

    @Modifying
    @Query(nativeQuery = true, value="delete from participation_serie where serie_id=:serie_id")
    void deleteBySerie(@Param("serie_id") Long serieId);

    @Query(nativeQuery = true, value="select * from participation_serie where acteur_id=:acteur_id and serie_id=:serie_id")
    ParticipationSerieEntity search(@Param("acteur_id") Long acteurId, @Param("serie_id") Long serieId);

    @Modifying
    @Query(nativeQuery = true, value="insert into participation_serie(acteur_id,serie_id,importance) values(:acteur_id,:serie_id,:importance)")
    void enregistrerParticipation(@Param("acteur_id") Long acteurId, @Param("serie_id") Long serieId, @Param("importance") String importance);
}
