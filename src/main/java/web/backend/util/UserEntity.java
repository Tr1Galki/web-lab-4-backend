package web.backend.util;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserEntity {
    @Id
    private Integer id;
    private String user;
}
