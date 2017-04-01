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
    public ResponseEntity<ActeurEntity> addNouvelActeur(@RequestBody @Valid ActeurEntity acteurEntity, BindingResult bindingResult) {
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
}
