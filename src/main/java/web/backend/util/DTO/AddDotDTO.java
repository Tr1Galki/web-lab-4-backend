package web.backend.util.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AddDotDTO {
    private Double x;
    private Double y;
    private Double r;
    private Double date;
    private String owner;
}
