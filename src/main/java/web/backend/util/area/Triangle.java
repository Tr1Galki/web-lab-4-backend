package web.backend.util.area;

public class Triangle extends Shape {
    public Triangle(Integer quarter) {
        super(quarter);
    }

    @Override
    public Boolean isInArea(Double x, Double y, Double r) {
        return  y > -2 * x - r;
    }
}
