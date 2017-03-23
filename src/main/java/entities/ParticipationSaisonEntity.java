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

    public final static int PARTICIPATION_INCONNUE = 0;
    public final static int PARTICIPATION_PRINCIPALE = 1;
    public final static int PARTICIPATION_SECONDAIRE = 2;
    public final static int PARTICIPATION_TERTIAIRE = 3;


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
