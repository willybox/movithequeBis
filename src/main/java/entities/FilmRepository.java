package entities;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Willy on 11/04/2017.
 */
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
}
