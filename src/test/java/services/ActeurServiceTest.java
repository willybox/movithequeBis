package services;

import entities.ActeurEntity;
import entities.ActeurRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActeurServiceTest {

    @InjectMocks
    ActeurService acteurService;

    @Mock
    ActeurRepository acteurRepository;

    @Before
    public void init(){
        List<ActeurEntity> list = new ArrayList<ActeurEntity>();
        list.add(ActeurEntity.builder().id(1L).nom("dabitude").prenom("jojo").build());
        list.add(ActeurEntity.builder().id(2L).nom("azer").prenom("ty").build());
        list.add(ActeurEntity.builder().id(3L).nom("gg").prenom("wp").build());
        when(acteurRepository.findAll()).thenReturn(list);

        ActeurEntity z = any(ActeurEntity.class);
        when(acteurRepository.save(z)).thenReturn(z);

    }

    @Test
    public void acteur_doit_etre_cree(){
        ActeurEntity acteurEntity = ActeurEntity.builder().nom("ui").prenom("ui").build();

        int count = acteurService.getActeurs().size();

        assertThat(acteurService.getActeurs().size()).isEqualTo(count + 1);
    }
}
