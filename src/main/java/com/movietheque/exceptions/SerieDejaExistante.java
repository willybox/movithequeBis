package com.movietheque.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "La série existe déjà")
public class SerieDejaExistante  extends RuntimeException{
}
