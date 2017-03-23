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
@Table(name="episode")
public class EpisodeEntity {

    @Id
    @GeneratedValue
    @Column(name="episode_id")
    private Long id;

    @Column(name="episode_nom")
    private String nom;

    @Column(name="episode_numero")
    private int numero;

    @ManyToOne
    @JoinColumn(name="serie_id")
    private SerieEntity serie;

    @ManyToOne
    @JoinColumn(name="saison_id")
    private SaisonEntity saison;



}
