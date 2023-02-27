package web.backend.util.DTO;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import web.backend.util.DotEntity;

import java.io.Serializable;
import java.util.LinkedList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SendDotsDTO implements Serializable {
    private LinkedList<DotEntity> dots;

    public SendDotsDTO (String jsonString) {
        Gson gson = new Gson();
        SendDotsDTO tempDTO = gson.fromJson(jsonString, SendDotsDTO.class);
        this.dots = tempDTO.getDots();
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(new SendDotsDTO(dots));
    }
}
