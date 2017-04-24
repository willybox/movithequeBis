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
    public ResponseEntity addNouvelActeur(@Valid ActeurEntity acteurEntity,
                                          @RequestParam(value="selectFilm1",required = false) List<String> selectFilm1,
                                          @RequestParam(value="selectFilm2",required = false) List<String> selectFilm2,
                                          @RequestParam(value="selectFilm3",required = false) List<String> selectFilm3,
                                          Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }



        acteurService.createActeur(
                acteurEntity,
                getCorrectIdList(selectFilm1),
                getCorrectIdList(selectFilm2),
                getCorrectIdList(selectFilm3)
        );

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

    /**
     * <p>A propos des listes, le javascript retourne une liste sous la forme suivante:
     * <br />["1","2",...,"n"]
     * <br />List< String > list;
     * <br />list.get(0) = ["1"
     * <br />list.get(2) = "2"
     * <br />...
     * <br />list.get(n) = "n"]
     * </p>
     * @param acteurId
     * @param selectFilm1
     * @param selectFilm2
     * @param selectFilm3
     * @param acteurEntity
     * @param errors
     * @return
     */
    @PutMapping(path="/update/{acteur_id}")
    @ResponseBody
    public ResponseEntity updateActeur(@PathVariable("acteur_id") Long acteurId,
                                       @RequestParam("selectFilm1") List<String> selectFilm1,
                                       @RequestParam("selectFilm2") List<String> selectFilm2,
                                       @RequestParam("selectFilm3") List<String> selectFilm3,
                                       @Valid ActeurEntity acteurEntity,Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        acteurService.modifierActeur(
            acteurId,
            acteurEntity,
            getCorrectIdList(selectFilm1),
            getCorrectIdList(selectFilm2),
            getCorrectIdList(selectFilm3)
        );
        return new ResponseEntity<ActeurEntity>(acteurEntity, HttpStatus.OK);
    }

    /**
     * <p>Rappel:
     * <br />["1","2",...,"n"]
     * <br />List< String > list;
     * <br />list.get(0) = ["1"
     * <br />list.get(2) = "2"
     * <br />...
     * <br />list.get(n) = "n"]
     * </p>
     * @param filmSelection
     * @return
     */
    private List<Long> getCorrectIdList(List<String> filmSelection) {
        List<Long> listeFilmId = new ArrayList<Long>();
        if(filmSelection != null){
            for(String s : filmSelection){
                s = s.replaceAll("\"", "");
                s = s.replaceAll("\\]", "");
                s = s.replaceAll("\\[", "");
                if(!s.equals(""))
                    listeFilmId.add(Long.valueOf(s));
            }
        }
        return listeFilmId;
    }
}


