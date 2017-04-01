package controllers;

import entities.ActeurEntity;
import entities.ActeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.ActeurService;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/api/acteur/")
public class ActeurApiController {

    @Autowired
    private ActeurService acteurService;

    @PostMapping(path="/add")
    public ResponseEntity<ActeurEntity> addNouvelActeur(@Valid ActeurEntity acteurEntity, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

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

    @PutMapping(path="/update/{acteur_id}")
    @ResponseStatus(HttpStatus.OK)
    public ActeurEntity update(@PathVariable("acteur_id") Long acteurId, @Valid ActeurEntity acteurEntity, BindingResult bindingResult) {
        acteurService.modifierActeur(acteurId, acteurEntity);
        return acteurEntity;
    }
}
