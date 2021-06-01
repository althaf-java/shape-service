package co.uk.pupil.share.service.service;

import co.uk.pupil.share.service.exception.ShapeOverlapException;
import co.uk.pupil.share.service.model.Shape;

import java.util.List;

public interface ShapeService {

    Shape save(Shape shape) throws ShapeOverlapException;

    List<? extends Shape> findAll();
}
