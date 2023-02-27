package web.backend.util.DTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AddDotDTO implements Serializable {
    private Double x;
    private Double y;
    private Double r;
    private Long date;
    private String owner;

    public AddDotDTO(String jsonString) {
        Gson gson = new Gson();
        AddDotDTO tempDTO = gson.fromJson(jsonString, AddDotDTO.class);
        this.x = tempDTO.getX();
        this.y = tempDTO.getY();
        this.r = tempDTO.getR();
        this.date = tempDTO.getDate();
        this.owner = tempDTO.getOwner();
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(new AddDotDTO(x, y, r, date, owner));
    }
}
