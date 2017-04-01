package entities;

import enumerations.ImportanceEnum;
import lombok.*;
import org.springframework.context.annotation.Import;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="participation_film")
public class ParticipationFilmEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
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
