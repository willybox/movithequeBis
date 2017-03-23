package entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="participation_film")
public class ParticipationFilmEntity {

    private final static int PARTICIPATION_PRINCIPALE = 1;
    private final static int PARTICIPATION_SECONDAIRE = 2;
    private final static int PARTICIPATION_TERTIAIRE = 3;

    @Column(name="participation")
    private int participation;

    @Id
    @ManyToOne
    @JoinColumn(name="acteur_id")
    private ActeurEntity acteur;

    @Id
    @ManyToOne
    @JoinColumn(name="film_id")
    private FilmEntity film;

}
