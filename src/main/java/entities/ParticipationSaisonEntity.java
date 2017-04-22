package entities;

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
@Table(name="participation_saison")
public class ParticipationSaisonEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name="saison_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private ImportanceEnum importance;

    @Id
    @ManyToOne
    @JoinColumn(name="acteur_id")
    private ActeurEntity acteur;

    @Id
    @ManyToOne
    @JoinColumn(name="saison_id")
    private SaisonEntity saison;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipationSaisonEntity that = (ParticipationSaisonEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (importance != that.importance) return false;
        if (acteur != null ? !acteur.equals(that.acteur) : that.acteur != null) return false;
        return saison != null ? saison.equals(that.saison) : that.saison == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        result = 31 * result + (acteur != null ? acteur.hashCode() : 0);
        result = 31 * result + (saison != null ? saison.hashCode() : 0);
        return result;
    }
}
