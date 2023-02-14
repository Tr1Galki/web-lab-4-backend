package web.backend.util.area;

import web.backend.util.DotEntry;

public class Square extends Shape {
    public Square(Integer quarter) {
        super(quarter);
    }

    @Override
    public Boolean isInArea(Double x, Double y, Double r) {
        return x < r && y < r;
    }
}
