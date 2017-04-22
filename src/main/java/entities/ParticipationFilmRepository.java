package entities;

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
}
