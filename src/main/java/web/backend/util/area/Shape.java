package web.backend.util.area;

import lombok.Data;
import web.backend.util.DotEntry;

@Data
public abstract class Shape {
    private Integer quarter;

    public Shape(Integer quarter) {
        this.quarter = quarter;
    }

    public abstract Boolean isInArea(Double x, Double y, Double r);
}
