package entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="saison")
public class SaisonEntity {
    @Id
    @GeneratedValue
    @Column(name="saison_id")
    private Long id;

    @Column(name="saison_numero")
    private int numero;

    @OneToMany(mappedBy = "saison")
    private List<ParticipationSaisonEntity> participationSaisonList;

    @OneToMany(mappedBy = "saison")
    private List<EpisodeEntity> episodeList;

}
