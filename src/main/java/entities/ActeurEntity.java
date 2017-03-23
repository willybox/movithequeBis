package entities;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name="acteur")
public class ActeurEntity {

    @Id
    @GeneratedValue
    @Column(name="acteur_id")
    private Long id;

    @Column(name="acteur_nom")
    private String nom;

    @Column(name="acteur_prenom")
    private String prenom;

    @Column(name="acteur_datedenaissance")
    private Date dateDeNaissance;

    @OneToMany(mappedBy = "participation_saison")
    private List<ParticipationSaisonEntity> participationSaisonList;

    @OneToMany(mappedBy = "participation_film")
    private List<ParticipationFilmEntity> participationFilmList;

    public ActeurEntity(){
        participationSaisonList = new ArrayList<ParticipationSaisonEntity>();
        participationFilmList = new ArrayList<ParticipationFilmEntity>();
    }
}
