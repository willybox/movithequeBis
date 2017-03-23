package entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="serie")
public class SerieEntity {
    @Id
    @GeneratedValue
    @Column(name="serie_id")
    private Long id;

    @Column(name="serie_name")
    private String name;

    @OneToMany(mappedBy = "EpisodeEntity")
    private List<EpisodeEntity> episodeList;
}
