package com.movietheque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movietheque.enumerations.ImportanceEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="participation_serie")
@IdClass(ParticipationSerieEntity.class)
public class ParticipationSerieEntity implements Serializable{


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
    @JoinColumn(name="serie_id")
    @JsonIgnore
    private SerieEntity serie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipationSerieEntity that = (ParticipationSerieEntity) o;

        if (importance != that.importance) return false;
        if (acteur != null ? !acteur.equals(that.acteur) : that.acteur != null) return false;
        return serie != null ? serie.equals(that.serie) : that.serie == null;
    }

    @Override
    public int hashCode() {
        int result = importance != null ? importance.hashCode() : 0;
        result = 31 * result + (acteur != null ? acteur.hashCode() : 0);
        result = 31 * result + (serie != null ? serie.hashCode() : 0);
        return result;
    }
}
