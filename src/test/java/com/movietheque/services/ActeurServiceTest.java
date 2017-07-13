package com.movietheque.services;

import com.movietheque.entities.ActeurEntity;
import com.movietheque.enumerations.ImportanceEnum;
import com.movietheque.repositories.ActeurRepository;
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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ActeurServiceTest {

    @InjectMocks
    ActeurService acteurService;

    @Mock
    ActeurRepository acteurRepository;

    @Mock
    ParticipationFilmService participationFilmService;

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
    public void acteur_creation_doit_etre_appele(){
        ActeurEntity acteurEntity = any(ActeurEntity.class);
        List<Long> l1 = Arrays.asList(1L);
        List<Long> l2 = Arrays.asList(2L);
        List<Long> l3 = Arrays.asList(3L);

        acteurService.createActeur(acteurEntity, l1, l2, l3);

        verify(acteurRepository).save(acteurEntity);
        verify(participationFilmService).addParticipationsActeur(l1, acteurEntity, ImportanceEnum.IMPORTANCE_PRINCIPALE);
        verify(participationFilmService).addParticipationsActeur(l2, acteurEntity, ImportanceEnum.IMPORTANCE_SECONDAIRE);
        verify(participationFilmService).addParticipationsActeur(l3, acteurEntity, ImportanceEnum.IMPORTANCE_INCONNUE);
    }


    @Test
    public void acteur_supprime_doit_etre_appele(){
        Long deletionId = any(Long.class);

        acteurService.deleteActeur(deletionId);

        verify(participationFilmService).deleteParticipationFilmFromActeur(deletionId);
        verify(acteurRepository).delete(deletionId);
    }


}
