package web.backend.util.area;

public class AreaChecker {

    private Shape circle = new Circle(4);
    private Shape square = new Square(1);
    private Shape triangle = new Circle(3);

    public Boolean isInArea (Double x, Double y, Double r) {
        if (x >= 0 && y >= 0) {
            return square.isInArea(x, y, r);
        } else if ((x >= 0 && y <= 0)) {
            return circle.isInArea(x, y, r);
        } else if (x <= 0 && y <= 0) {
            return triangle.isInArea(x, y, r);
        }
        return false;
    }
}
