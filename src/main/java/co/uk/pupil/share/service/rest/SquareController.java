package co.uk.pupil.share.service.rest;

import co.uk.pupil.share.service.exception.ShapeOverlapException;
import co.uk.pupil.share.service.model.Square;
import co.uk.pupil.share.service.service.impl.SquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SquareController {

    @Autowired
    private SquareService squareService;

    @RequestMapping(value = "/shape", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Square> addShape(@RequestBody Square square) throws ShapeOverlapException {
        Square squareToBeReturned = (Square)squareService.save(square);
        return ResponseEntity.status(HttpStatus.CREATED).body(squareToBeReturned);
    }

    @RequestMapping(value = "/shapes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Square> getAllShapes(){
        return squareService.findAll();
    }
}
