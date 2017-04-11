package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@AllArgsConstructor(suppressConstructorProperties=true)
@Table(name="participation_film")
@JsonSerialize(using = ParticipationSerializer.class)
public class ParticipationFilmEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private ImportanceEnum importance;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="acteur_id")
    private ActeurEntity acteur;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="film_id")
    private FilmEntity film;

}
