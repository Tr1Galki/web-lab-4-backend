package web.backend.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class DotDTO implements Serializable {
    private DotEntity dot;
    private String type;

    public DotDTO() {
    }

    public DotDTO(DotEntity dot, String type) {
        this.dot = dot;
        this.type = type;
    }
}
