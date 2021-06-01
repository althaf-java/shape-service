package co.uk.pupil.share.service.dao;

import co.uk.pupil.share.service.model.Point;
import co.uk.pupil.share.service.model.Square;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class SquareDaoTest {

    @Spy
    private SquareDao squareDao;

    @Test
    public void testIsOverLapExistReturnFalseWhenNoOverlapExist() {
        Square squareToBeReturned = new Square("One", new Point(2, 2), new Point(4, 4));
        Square squareToBeReturned2 = new Square("One", new Point(5, 5), new Point(7, 7));
        Mockito.when(squareDao.findAll()).thenReturn(Arrays.asList(squareToBeReturned, squareToBeReturned2));

        boolean overLapExist = squareDao.isOverLapExist(new Square("One", new Point(8, 8), new Point(10, 10)));
        Assert.assertFalse(overLapExist);
    }

    @Test
    public void testIsOverLapExistReturnTrueWhenOverlapExist() {
        Square squareToBeReturned = new Square("One", new Point(2, 2), new Point(4, 4));
        Square squareToBeReturned2 = new Square("One", new Point(3, 3), new Point(6, 6));
        Mockito.when(squareDao.findAll()).thenReturn(Arrays.asList(squareToBeReturned, squareToBeReturned2));

        boolean overLapExist = squareDao.isOverLapExist(new Square("One", new Point(4, 4), new Point(6, 6)));
        Assert.assertTrue(overLapExist);
    }

    @Test
    public void testIsOverLapExistReturnTrueWhenOverlapExistOnSideWays() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnTrueWhenOverlapExistOnHorizontally() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnTrueWhenOverlapExistFully() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnTrueWhenOverlapExistHalfway() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnTrueWhenOverlapExistBottomCorner() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnFalseWhenNoSharingOnSideways() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnFalseWhenSharingOnSideways() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnFalseWhenNoSharingOnHalfway() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnFalseWhenSharingOnHalfway() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnFalseWhenNoSharingCorner() {
        //Construction for the future
    }

    @Test
    public void testIsOverLapExistReturnFalseWhenSharingCorner() {
        //Construction for the future
    }
}
