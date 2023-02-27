package web.backend.util.DTO;

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
public class GetDotsDTO implements Serializable {
    private String owner;

     public GetDotsDTO fromJsonString(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, GetDotsDTO.class);
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(new GetDotsDTO(owner));
    }
}
