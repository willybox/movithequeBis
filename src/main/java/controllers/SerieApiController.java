package controllers;


import entities.FilmEntity;
import entities.SerieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import services.FilmService;
import services.SerieService;
import utils.Utils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/serie/")
public class SerieApiController {

    @Autowired
    private SerieService serieService;

    @PostMapping(path="/add")
    public ResponseEntity addNouvelSerie(@Valid SerieEntity serieEntity,
                                         @RequestParam(value="selectActeur1",required = false) List<String> selectActeur1,
                                         @RequestParam(value="selectActeur2",required = false) List<String> selectActeur2,
                                         @RequestParam(value="selectActeur3",required = false) List<String> selectActeur3,
                                         Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        serieService.createSerie(
                serieEntity,
                Utils.getCorrectIdList(selectActeur1),
                Utils.getCorrectIdList(selectActeur2),
                Utils.getCorrectIdList(selectActeur3)
        );

        return new ResponseEntity<SerieEntity>(serieEntity, HttpStatus.CREATED);
    }

    @GetMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<SerieEntity> getSeries() {
        return serieService.getSeries();
    }

    @PostMapping(path="/delete/{serie_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSerie(@PathVariable("serie_id") Long serieId) {
        serieService.deleteSerie(serieId);
    }

    @GetMapping("/{serie_id}")
    public SerieEntity getSerie(@PathVariable("serie_id") Long serieId){
        return serieService.getSerie(serieId);
    }

    @PutMapping(path="/update/{serie_id}")
    public ResponseEntity updateSerie(@PathVariable("serie_id") Long serieId,
                                     @RequestParam(value="selectActeur1",required = false) List<String> selectActeur1,
                                     @RequestParam(value="selectActeur2",required = false) List<String> selectActeur2,
                                     @RequestParam(value="selectActeur3",required = false) List<String> selectActeur3,
                                     @Valid SerieEntity serieEntity, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        serieService.modifierSerie(serieId,
                serieEntity,
                Utils.getCorrectIdList(selectActeur1),
                Utils.getCorrectIdList(selectActeur2),
                Utils.getCorrectIdList(selectActeur3)
        );
        return new ResponseEntity<SerieEntity>(serieEntity, HttpStatus.OK);
    }
}
