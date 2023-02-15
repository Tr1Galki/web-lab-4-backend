package web.backend.util;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class DotEntry {
    @Id
    private Long id;
    private Boolean inArea;
    private Double x, y, r;
    private Date date;
    private Integer time;
    private String owner;
    private String creator;
}
