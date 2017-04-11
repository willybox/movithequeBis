package entities;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name="acteur",uniqueConstraints={@UniqueConstraint(columnNames = {"acteur_nom" , "acteur_prenom"})})
public class ActeurEntity {
    @Id
    @GeneratedValue
    @Column(name="acteur_id")
    private Long id;

    @Column(name="acteur_nom")
    @NotNull(message = "Le nom ne peut être vide")
    @NotBlank(message = "Le nom ne peut être vide")
    private String nom;

    @Column(name="acteur_prenom")
    @NotNull(message = "Le prenom ne peut être vide")
    @NotBlank(message = "Le prenom ne peut être vide")
    private String prenom;

    @Column(name="acteur_datedenaissance")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateDeNaissance;

    //@Column(name="acteur_nationalite")


    @OneToMany(mappedBy = "acteur")
    private List<ParticipationSaisonEntity> participationSaisonList;

    @OneToMany(mappedBy = "acteur")
    private List<ParticipationFilmEntity> participationFilmList;

    public ActeurEntity(){
        participationSaisonList = new ArrayList<ParticipationSaisonEntity>();
        participationFilmList = new ArrayList<ParticipationFilmEntity>();
    }


}
