package web.backend.util.area;

public class Square extends Shape {
    public Square(Integer quarter) {
        super(quarter);
    }

    @Override
    public Boolean isInArea(Double x, Double y, Double r) {
        return x < r && y < r;
    }
}
