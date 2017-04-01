package entities;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @NotBlank
    private String nom;

    @Column(name="acteur_prenom")
    @NotNull
    @NotBlank
    private String prenom;

    @Column(name="acteur_datedenaissance")
    private Date dateDeNaissance;

    @OneToMany(mappedBy = "acteur")
    private List<ParticipationSaisonEntity> participationSaisonList;

    @OneToMany(mappedBy = "acteur")
    private List<ParticipationFilmEntity> participationFilmList;

    public ActeurEntity(){
        participationSaisonList = new ArrayList<ParticipationSaisonEntity>();
        participationFilmList = new ArrayList<ParticipationFilmEntity>();
    }


}
