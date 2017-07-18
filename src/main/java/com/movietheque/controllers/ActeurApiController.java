package com.movietheque.controllers;

import com.movietheque.entities.ActeurEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.movietheque.services.ActeurService;
import com.movietheque.utils.Utils;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api/acteur/")
public class ActeurApiController {

    @Autowired
    private ActeurService acteurService;

    @PostMapping(path="/add")
    public ResponseEntity addNouvelActeur(@Valid ActeurEntity acteurEntity,
                                          @RequestParam(value="selectFilm1",required = false) List<String> selectFilm1,
                                          @RequestParam(value="selectFilm2",required = false) List<String> selectFilm2,
                                          @RequestParam(value="selectFilm3",required = false) List<String> selectFilm3,
                                          Errors errors) {
        return null;
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
    public ResponseEntity updateActeur(@PathVariable("acteur_id") Long acteurId,
                                       @RequestParam(value="selectFilm1",required = false) List<String> selectFilm1,
                                       @RequestParam(value="selectFilm2",required = false) List<String> selectFilm2,
                                       @RequestParam(value="selectFilm3",required = false) List<String> selectFilm3,
                                       @Valid ActeurEntity acteurEntity,Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        acteurService.modifierActeur(
            acteurId,
            acteurEntity,
            Utils.getCorrectIdList(selectFilm1),
            Utils.getCorrectIdList(selectFilm2),
            Utils.getCorrectIdList(selectFilm3)
        );
        return new ResponseEntity<ActeurEntity>(acteurEntity, HttpStatus.OK);
    }


}


