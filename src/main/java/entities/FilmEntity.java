package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jsonserializer.ParticipationFilmFilmSerializer;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name="film",uniqueConstraints={@UniqueConstraint(columnNames = {"film_nom"})})
public class FilmEntity {

    @Id
    @GeneratedValue
    @Column(name="film_id")
    private Long id;

    @Column(name="film_nom")
    @NotNull(message = "Le nom ne peut être vide")
    @NotBlank(message = "Le nom ne peut être vide")
    private String nom;

    @Column(name="film_datedesortie")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date dateDeSortie;

    @OneToMany(targetEntity = ParticipationFilmEntity.class,mappedBy = "film")
    @JsonSerialize(using = ParticipationFilmFilmSerializer.class)
    @JsonProperty("acteursList")
    private List<ParticipationFilmEntity> participationFilmList;

    public FilmEntity(){
        participationFilmList = new ArrayList<ParticipationFilmEntity>();
    }
}
