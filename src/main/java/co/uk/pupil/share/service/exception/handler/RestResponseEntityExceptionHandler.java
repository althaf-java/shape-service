package co.uk.pupil.share.service.exception.handler;

import co.uk.pupil.share.service.exception.ShapeOverlapException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { ConstraintViolationException.class })
    protected ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        return new ResponseEntity<String>(
                "Some other shape already has the same name", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value
            = { ShapeOverlapException.class })
    protected ResponseEntity<String> handleShapeOverlapException(ShapeOverlapException shapeOverlapException) {
        return new ResponseEntity<String>(
                "Shape is overlapped with some other shape", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
