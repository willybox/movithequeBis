package entities;

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

    private final static int PARTICIPATION_PRINCIPALE = 1;
    private final static int PARTICIPATION_SECONDAIRE = 2;
    private final static int PARTICIPATION_TERTIAIRE = 3;

    @Column(name="participation")
    private int participation;

    @Id
    @ManyToOne
    @JoinColumn(name="acteur_id")
    private ActeurEntity actor;

    @Id
    @ManyToOne
    @JoinColumn(name="saison_id")
    private SaisonEntity saison;

}
