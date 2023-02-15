package web.backend.util.area;


public class Circle extends Shape{

    public Circle(Integer quarter) {
        super(quarter);
    }

    @Override
    public Boolean isInArea(Double x, Double y, Double r) {
        return Math.sqrt(x * x + y * y) <= r / 2;
    }
}
