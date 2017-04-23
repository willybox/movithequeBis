package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enumerations.ImportanceEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="participation_film")
@IdClass(ParticipationFilmEntity.class)
public class ParticipationFilmEntity implements Serializable{


    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private ImportanceEnum importance;

    @Id
    @ManyToOne
    @JoinColumn(name="acteur_id")
    @JsonIgnore
    private ActeurEntity acteur;

    @Id
    @ManyToOne
    @JoinColumn(name="film_id")
    @JsonIgnore
    private FilmEntity film;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipationFilmEntity that = (ParticipationFilmEntity) o;

        if (importance != that.importance) return false;
        if (acteur != null ? !acteur.equals(that.acteur) : that.acteur != null) return false;
        return film != null ? film.equals(that.film) : that.film == null;
    }

    @Override
    public int hashCode() {
        int result = importance != null ? importance.hashCode() : 0;
        result = 31 * result + (acteur != null ? acteur.hashCode() : 0);
        result = 31 * result + (film != null ? film.hashCode() : 0);
        return result;
    }
}
