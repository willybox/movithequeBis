package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jsonserializer.ParticipationFilmFilmSerializer;
import jsonserializer.ParticipationSerieSerieSerializer;
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
@AllArgsConstructor(suppressConstructorProperties=true)
@Table(name="serie",uniqueConstraints={@UniqueConstraint(columnNames = {"serie_nom"})})
public class SerieEntity {

    @Id
    @GeneratedValue
    @Column(name="serie_id")
    private Long id;

    @Column(name="serie_nom")
    @NotNull(message = "Le nom ne peut être vide")
    @NotBlank(message = "Le nom ne peut être vide")
    private String nom;

    @Column(name="serie_datedesortie")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date dateDeSortie;

    @OneToMany(targetEntity = ParticipationSerieEntity.class,mappedBy = "serie")
    @JsonSerialize(using = ParticipationSerieSerieSerializer.class)
    @JsonProperty("acteursList")
    private List<ParticipationSerieEntity> participationSerieList;

    public SerieEntity(){
        participationSerieList = new ArrayList<ParticipationSerieEntity>();
    }
}
