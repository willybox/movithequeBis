package controllers;

import entities.ActeurEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import services.ActeurService;

import javax.validation.Valid;

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
    public ResponseEntity updateActeur(@PathVariable("acteur_id") Long acteurId, @Valid ActeurEntity acteurEntity, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        acteurService.modifierActeur(acteurId, acteurEntity);
        return new ResponseEntity<ActeurEntity>(acteurEntity, HttpStatus.OK);
    }
}
