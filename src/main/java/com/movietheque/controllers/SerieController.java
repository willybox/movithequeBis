package com.movietheque.controllers;


import com.movietheque.entities.ActeurEntity;
import com.movietheque.entities.SerieEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/series")
public class SerieController {

    @RequestMapping("/")
    public String series(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String urlApiListeSeries = "http://localhost:8080/api/serie/";
        String urlApiListeActeurs = "http://localhost:8080/api/acteur/";
        ResponseEntity<List<SerieEntity>> reponseApiListeSeries = restTemplate.exchange(urlApiListeSeries, HttpMethod.GET, null,new ParameterizedTypeReference<List<SerieEntity>>() {});
        ResponseEntity<List<ActeurEntity>> reponseApiListeActeurs = restTemplate.exchange(urlApiListeActeurs, HttpMethod.GET, null,new ParameterizedTypeReference<List<ActeurEntity>>() {});
        model.addAttribute("seriesListe",reponseApiListeSeries.getBody());
        model.addAttribute("acteursListe",reponseApiListeActeurs.getBody());
        return "series";
    }
}
