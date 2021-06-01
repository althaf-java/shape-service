package co.uk.pupil.share.service.service.impl;

import co.uk.pupil.share.service.dao.SquareDao;
import co.uk.pupil.share.service.exception.ShapeOverlapException;
import co.uk.pupil.share.service.model.Shape;
import co.uk.pupil.share.service.model.Square;
import co.uk.pupil.share.service.service.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SquareService implements ShapeService {

    @Autowired
    private SquareDao squareDao;

    public Shape save(Shape square) throws ShapeOverlapException {
        if (!squareDao.isOverLapExist((Square)square)) {
            return squareDao.save((Square)square);
        }
        else {
            throw new ShapeOverlapException();
        }
    }

    public List<Square> findAll() {
        return squareDao.findAll();
    }
}
