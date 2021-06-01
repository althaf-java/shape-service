package co.uk.pupil.share.service.service.impl;

import co.uk.pupil.share.service.dao.SquareDao;
import co.uk.pupil.share.service.exception.ShapeOverlapException;
import co.uk.pupil.share.service.model.Square;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SquareServiceTest {

    @InjectMocks
    private SquareService  squareService;

    @Mock
    private SquareDao squareDao;

    @Test
    public void testSaveReturnTrueWhenNoOverlapExist() throws ShapeOverlapException {
        Mockito.when(squareDao.isOverLapExist(Mockito.any(Square.class))).thenReturn(false);
        Square squareToBeReturned = new Square();
        Mockito.when(squareDao.save(Mockito.any(Square.class))).thenReturn(squareToBeReturned);
        Square squareReturned = (Square)squareService.save(new Square());

        Assert.assertEquals(squareToBeReturned, squareReturned);
        Mockito.verify(squareDao, Mockito.times(1)).save(Mockito.any(Square.class));
    }

    @Test
    public void testSaveReturnFalseWhenOverlapExist() throws ShapeOverlapException {
        Mockito.when(squareDao.isOverLapExist(Mockito.any(Square.class))).thenReturn(true);

        Square squareReturned = (Square)squareService.save(new Square());
        Assert.assertNull(squareReturned);
        Mockito.verify(squareDao, Mockito.times(0)).save(Mockito.any(Square.class));
    }

    @Test
    public void testFindAll() {
        Square squareToBeReturned = new Square();
        Mockito.when(squareDao.findAll()).thenReturn(Arrays.asList(squareToBeReturned));

        List<Square> squareReturned = squareDao.findAll();
        Assert.assertEquals(squareReturned, (Arrays.asList(squareToBeReturned)));
    }
}
