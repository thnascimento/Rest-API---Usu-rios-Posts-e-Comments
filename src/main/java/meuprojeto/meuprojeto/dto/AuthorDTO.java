package meuprojeto.meuprojeto.dto;

import lombok.Builder;
import lombok.Data;
import meuprojeto.meuprojeto.domain.User;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Builder
@Data
public class AuthorDTO {

    private String id;
    private String name;
}