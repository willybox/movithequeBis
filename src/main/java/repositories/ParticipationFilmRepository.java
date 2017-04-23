package repositories;

import entities.ParticipationFilmEntity;
import enumerations.ImportanceEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationFilmRepository extends JpaRepository<ParticipationFilmEntity, Long> {

    @Modifying
    @Query(nativeQuery = true, value="delete from participation_film where acteur_id=:acteur_id")
    void deleteByActeur(@Param("acteur_id") Long acteurId);

    @Modifying
    @Query(nativeQuery = true, value="delete from participation_film where film_id=:film_id")
    void deleteByFilm(@Param("film_id") Long filmId);

    @Query(nativeQuery = true, value="select * from participation_film where acteur_id=:acteur_id and film_id=:film_id")
    ParticipationFilmEntity search(@Param("acteur_id") Long acteurId,@Param("film_id") Long filmId);

    @Modifying
    @Query(nativeQuery = true, value="insert into participation_film(acteur_id,film_id,importance) values(:acteur_id,:film_id,:importance)")
    void saveTest(@Param("acteur_id") Long acteurId,@Param("film_id") Long filmId,@Param("importance") String importance);
}
