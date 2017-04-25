package repositories;

import entities.FilmEntity;
import entities.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {
    SerieEntity findById(Long serieId);
}
