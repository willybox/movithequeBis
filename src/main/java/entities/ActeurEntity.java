package entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@AllArgsConstructor(suppressConstructorProperties=true)
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
    private Date dateDeNaissance;

    @OneToMany(mappedBy = "acteur")
    private List<ParticipationSaisonEntity> participationSaisonList;

    @OneToMany(targetEntity = ParticipationFilmEntity.class, mappedBy = "acteur", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty("filmsList")
    private List<ParticipationFilmEntity> participationFilmList;

    public ActeurEntity(){
        participationSaisonList = new ArrayList<ParticipationSaisonEntity>();
        participationFilmList = new ArrayList<ParticipationFilmEntity>();
    }


}

