package web.backend.util;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class DotEntity implements Serializable {
    @Id
    private Long id;
    private Boolean inArea;
    private Double x, y, r;
    private Integer date;
    private Integer time;
    private String owner;
    private String creator;

    public DotEntity() {

    }

    public DotEntity(Double x, Double y, Double r, Integer date, String owner, String creator) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.date = date;
        this.owner = owner;
        this.creator = creator;
    }
}
