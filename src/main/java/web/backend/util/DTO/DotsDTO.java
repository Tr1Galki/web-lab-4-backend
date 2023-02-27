package web.backend.util.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;
import web.backend.util.DotEntity;

import java.io.Serializable;
import java.util.LinkedList;

@Data
@Component
public class DotsDTO implements Serializable {
    private LinkedList<DotEntity> dots;

    public void addDot(DotEntity dot) {
        if (dots == null) {
            dots = new LinkedList<>();
        }
        dots.add(dot);
    }
}
