package controllers;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;


public class ValidationErrorBuilder {
    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            if(objectError.getDefaultMessage().contains("@org.springframework.format.annotation.DateTimeFormat")){
                error.addValidationError("Format de date invalide");
            }
            else{
                error.addValidationError(objectError.getDefaultMessage());
            }
        }
        return error;
    }
}
