package controllers;

import entities.ActeurEntity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import services.ActeurService;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api/acteur/")
public class ActeurApiController {

    @Autowired
    private ActeurService acteurService;

    @PostMapping(path="/add")
    public ResponseEntity addNouvelActeur(@Valid ActeurEntity acteurEntity, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        acteurService.createActeur(acteurEntity);

        return new ResponseEntity<ActeurEntity>(acteurEntity, HttpStatus.CREATED);
    }

    @GetMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<ActeurEntity> getActeurs() {
        return acteurService.getActeurs();
    }

    @PostMapping(path="/delete/{acteur_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActeur(@PathVariable("acteur_id") Long acteurId) {
        acteurService.deleteActeur(acteurId);
    }

    @GetMapping("/{acteur_id}")
    public ActeurEntity getAccount(@PathVariable("acteur_id") Long acteurId){
        return acteurService.getActeur(acteurId);
    }

    @PutMapping(path="/update/{acteur_id}")
    @ResponseBody
    public ResponseEntity updateActeur(@PathVariable("acteur_id") Long acteurId, @RequestParam("selectFilm1") List<String> selectFilm1, @RequestParam("selectFilm2") List<String> selectFilm2, @RequestParam("selectFilm3") List<String> selectFilm3, @Valid ActeurEntity acteurEntity,Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        List<Long> listeFilmId1 = new ArrayList<Long>();
        List<Long> listeFilmId2 = new ArrayList<Long>();
        List<Long> listeFilmId3 = new ArrayList<Long>();

        for(String s : selectFilm1){
            s = s.replaceAll("\"", "");
            s = s.replaceAll("\\]", "");
            s = s.replaceAll("\\[", "");
            if(!s.equals(""))
                listeFilmId1.add(Long.valueOf(s));
        }
        for(String s : selectFilm2){
            s = s.replaceAll("\"", "");
            s = s.replaceAll("\\]", "");
            s = s.replaceAll("\\[", "");
            if(!s.equals(""))
                listeFilmId2.add(Long.valueOf(s));
        }
        for(String s : selectFilm3){
            s = s.replaceAll("\"", "");
            s = s.replaceAll("\\]", "");
            s = s.replaceAll("\\[", "");
            if(!s.equals(""))
                listeFilmId3.add(Long.valueOf(s));
        }
        acteurService.modifierActeur(acteurId, acteurEntity,listeFilmId1,listeFilmId2,listeFilmId3);
        return new ResponseEntity<ActeurEntity>(acteurEntity, HttpStatus.OK);
    }
}
