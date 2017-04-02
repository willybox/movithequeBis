package controllers;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by Jo on 02/04/2017.
 */
public class ValidationErrorBuilder {
    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}
