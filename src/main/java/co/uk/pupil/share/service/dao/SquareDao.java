package co.uk.pupil.share.service.dao;

import co.uk.pupil.share.service.model.Square;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquareDao extends JpaRepository<Square, Integer>{

    /**
     * TODO: This method can be converted into a query value method.
     * @param square
     * @return
     */
    default boolean isOverLapExist(Square square) {
        List<Square> lstSquare = findAll();
        return lstSquare.stream().filter((s) -> {
            if (s.getTopRight().getyCoordinate() < square.getBottomLeft().getyCoordinate()
                    || s.getBottomLeft().getyCoordinate() > square.getTopRight().getyCoordinate()) {
                return false;
            }
            if (s.getTopRight().getxCoordinate() < square.getBottomLeft().getxCoordinate()
                    || s.getBottomLeft().getxCoordinate() > square.getTopRight().getxCoordinate()) {
                return false;
            }
            return true;
        }).count() > 0;
    }
}
