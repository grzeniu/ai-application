package pl.edu.wat.ai.app.interfaces.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin(origins = "*", maxAge = 3600)
public class GlobalControllerExceptionHandler {

}
