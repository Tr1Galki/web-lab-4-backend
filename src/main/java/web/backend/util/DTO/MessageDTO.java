package web.backend.util.DTO;

import lombok.*;
import org.springframework.stereotype.Component;
import web.backend.util.Status;

import java.io.Serializable;

@Data
@Component
public class MessageDTO implements Serializable {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
