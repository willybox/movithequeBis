package controllers;

import entities.ActeurEntity;
import entities.ActeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ActeurService;


@RestController
@RequestMapping("/api/acteur/")
public class ActeurApiController {

    @Autowired
    private ActeurRepository acteurRepository;



    @GetMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String nom , @RequestParam String prenom) {
        ActeurEntity acteur = ActeurEntity.builder().nom(nom).prenom(prenom).build();
        acteurRepository.save(acteur);
        return "Saved";
    }
}
