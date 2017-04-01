package controllers;

import entities.ActeurEntity;
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
        ResponseEntity<List<ActeurEntity>> reponseApiListeActeurs = restTemplate.exchange(urlApiListeActeurs, HttpMethod.GET, null,new ParameterizedTypeReference<List<ActeurEntity>>() {});
        model.addAttribute("acteursListe",reponseApiListeActeurs.getBody());
        return "acteurs";
    }
}
