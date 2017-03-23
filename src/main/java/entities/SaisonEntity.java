package entities;


import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

public class SaisonEntity {

    @Column(name="saison_numero")
    private int numero;

    @OneToMany(mappedBy = "participation_saison")
    private List<ParticipationSaisonEntity> participationSaisonList;

    @OneToMany(mappedBy = "EpisodeEntity")
    private List<EpisodeEntity> episodeList;

}
