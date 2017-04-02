package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "L'acteur existe déjà")
public class ActeurDejaExistant extends RuntimeException {
    private static final long serialVersionUID = 100L;
}
