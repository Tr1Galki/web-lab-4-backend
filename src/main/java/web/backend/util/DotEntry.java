package web.backend.util;

import lombok.Data;

import java.util.Date;

@Data
public class DotEntry {
    private Boolean inArea;
    private Double x, y, r;
    private Date date;
    private Integer time;
    private String owner;
    private String creator;
}
