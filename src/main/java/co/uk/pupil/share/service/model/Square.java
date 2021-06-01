package co.uk.pupil.share.service.model;

import javax.persistence.*;

@Entity
@Table(name = "SQUARE")
public class Square extends Shape {

    public Square() {
    }

    public Square(String name) {
        super(name);
    }

    public Square(String name, Point bottomLeft, Point topRight) {
        super(name);
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Point bottomLeft;

    @ManyToOne(cascade = CascadeType.ALL)
    private Point topRight;

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Point bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    public void setTopRight(Point topRight) {
        this.topRight = topRight;
    }
}
