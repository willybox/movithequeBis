package entities;

import enumerations.ImportanceEnum;
import lombok.*;
import org.springframework.context.annotation.Import;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="participation_film")
public class ParticipationFilmEntity {

    @Column(name="importance")
    private ImportanceEnum importance;

    @Id
    @ManyToOne
    @JoinColumn(name="acteur_id")
    private ActeurEntity acteur;

    @Id
    @ManyToOne
    @JoinColumn(name="film_id")
    private FilmEntity film;

}
