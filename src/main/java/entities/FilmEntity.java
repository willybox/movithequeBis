package entities;

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

    @OneToMany(mappedBy = "film")
    private List<ParticipationFilmEntity> participationFilmList;

    public FilmEntity(){
        participationFilmList = new ArrayList<ParticipationFilmEntity>();
    }
}
