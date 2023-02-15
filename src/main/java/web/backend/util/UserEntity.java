package web.backend.util;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserEntity {
    @Id
    private String id;
    private String phoneNumber;
}
