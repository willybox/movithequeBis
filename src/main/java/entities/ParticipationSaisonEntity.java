package entities;

import enumerations.ImportanceEnum;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="participation_saison")
public class ParticipationSaisonEntity {

    @Column(name="importance")
    private ImportanceEnum importance;

    @Id
    @ManyToOne
    @JoinColumn(name="acteur_id")
    private ActeurEntity actor;

    @Id
    @ManyToOne
    @JoinColumn(name="saison_id")
    private SaisonEntity saison;

}
