package web.backend.util;

import lombok.Data;

@Data
public class DotDTO {
    private DotEntity dot;
    private String type;

    public DotDTO() {
    }

    public DotDTO(DotEntity dot, String type) {
        this.dot = dot;
        this.type = type;
    }
}
