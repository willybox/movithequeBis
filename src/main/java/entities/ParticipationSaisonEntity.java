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

}
