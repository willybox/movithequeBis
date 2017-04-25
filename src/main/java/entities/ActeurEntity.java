package entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jsonserializer.ParticipationFilmActeurSerializer;
import jsonserializer.ParticipationSerieActeurSerializer;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date dateDeNaissance;

    @OneToMany(targetEntity = ParticipationSerieEntity.class,mappedBy = "serie")
    @JsonSerialize(using= ParticipationSerieActeurSerializer.class)
    @JsonProperty("seriesList")
    private List<ParticipationSerieEntity> participationSerieList;

    @OneToMany(targetEntity = ParticipationFilmEntity.class, mappedBy = "acteur")
    @JsonSerialize(using= ParticipationFilmActeurSerializer.class)
    @JsonProperty("filmsList")
    private List<ParticipationFilmEntity> participationFilmList;

    public ActeurEntity(){
        participationSerieList = new ArrayList<ParticipationSerieEntity>();
        participationFilmList = new ArrayList<ParticipationFilmEntity>();
    }


}

