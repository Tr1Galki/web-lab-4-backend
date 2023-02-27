package web.backend.util;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class DotEntity implements Serializable {
    @Id
    private Long id;
    private Integer inArea;
    private Double x, y, r;
    private Long date;
    private Long time;
    private String owner;
    public DotEntity() {

    }

    public DotEntity(Double x, Double y, Double r, Long date, String owner) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.date = date;
        this.owner = owner;
    }
}
