package entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name="film")
public class FilmEntity {

    @Id
    @GeneratedValue
    @Column(name="movie_id")
    private Long id;

    @Column(name="movie_name")
    private String name;

    @OneToMany(targetEntity = ParticipationFilmEntity.class,mappedBy = "film", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ParticipationFilmEntity> participationFilmList;

    public FilmEntity(){
        participationFilmList = new ArrayList<ParticipationFilmEntity>();
    }
}
