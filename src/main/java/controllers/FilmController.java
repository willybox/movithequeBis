package controllers;


import entities.ActeurEntity;
import entities.FilmEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {

    @RequestMapping("/")
    public String films(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String urlApiListeFilms = "http://localhost:8080/api/film/";
        String urlApiListeActeurs = "http://localhost:8080/api/acteur/";
        ResponseEntity<List<FilmEntity>> reponseApiListeFilms = restTemplate.exchange(urlApiListeFilms, HttpMethod.GET, null,new ParameterizedTypeReference<List<FilmEntity>>() {});
        ResponseEntity<List<ActeurEntity>> reponseApiListeActeurs = restTemplate.exchange(urlApiListeActeurs, HttpMethod.GET, null,new ParameterizedTypeReference<List<ActeurEntity>>() {});
        model.addAttribute("filmsListe",reponseApiListeFilms.getBody());
        model.addAttribute("acteursListe",reponseApiListeActeurs.getBody());
        return "films";
    }
}
