package com.movietheque.controllers;

import com.movietheque.entities.ActeurEntity;
import com.movietheque.entities.FilmEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/acteurs")
public class ActeurController {

    @RequestMapping("/")
    public String acteurs(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String urlApiListeActeurs = "http://localhost:8080/api/acteur/";
        String urlApiListeFilms = "http://localhost:8080/api/film/";
        ResponseEntity<List<ActeurEntity>> reponseApiListeActeurs = restTemplate.exchange(urlApiListeActeurs, HttpMethod.GET, null,new ParameterizedTypeReference<List<ActeurEntity>>() {});
        ResponseEntity<List<FilmEntity>> reponseApiListeFilms = restTemplate.exchange(urlApiListeFilms, HttpMethod.GET, null,new ParameterizedTypeReference<List<FilmEntity>>() {});
        model.addAttribute("acteursListe",reponseApiListeActeurs.getBody());
        model.addAttribute("filmsListe",reponseApiListeFilms.getBody());


        return "acteurs";
    }
}
