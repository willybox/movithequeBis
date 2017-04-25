package controllers;


import entities.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import services.FilmService;
import utils.Utils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/film/")
public class FilmApiController {

    @Autowired
    private FilmService filmService;

    @PostMapping(path="/add")
    public ResponseEntity addNouveauFilm(@Valid FilmEntity filmEntity,
                                         @RequestParam(value="selectActeur1",required = false) List<String> selectActeur1,
                                         @RequestParam(value="selectActeur2",required = false) List<String> selectActeur2,
                                         @RequestParam(value="selectActeur3",required = false) List<String> selectActeur3,
                                         Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        filmService.createFilm(
                filmEntity,
                Utils.getCorrectIdList(selectActeur1),
                Utils.getCorrectIdList(selectActeur2),
                Utils.getCorrectIdList(selectActeur3)
        );

        return new ResponseEntity<FilmEntity>(filmEntity, HttpStatus.CREATED);
    }

    @GetMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<FilmEntity> getFilms() {
        return filmService.getFilms();
    }

    @PostMapping(path="/delete/{film_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFilm(@PathVariable("film_id") Long filmId) {
        filmService.deleteFilm(filmId);
    }

    @GetMapping("/{film_id}")
    public FilmEntity getFilm(@PathVariable("film_id") Long filmId){
        return filmService.getFilm(filmId);
    }

    @PutMapping(path="/update/{film_id}")
    public ResponseEntity updateFilm(@PathVariable("film_id") Long filmId,
                                     @RequestParam("selectActeur1") List<String> selectActeur1,
                                     @RequestParam("selectActeur2") List<String> selectActeur2,
                                     @RequestParam("selectActeur3") List<String> selectActeur3,
                                     @Valid FilmEntity filmEntity, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        filmService.modifierFilm(filmId,
                filmEntity,
                Utils.getCorrectIdList(selectActeur1),
                Utils.getCorrectIdList(selectActeur2),
                Utils.getCorrectIdList(selectActeur3)
        );
        return new ResponseEntity<FilmEntity>(filmEntity, HttpStatus.OK);
    }
}
