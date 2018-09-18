package meuprojeto.meuprojeto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import meuprojeto.meuprojeto.dto.validation.CaseMode;
import meuprojeto.meuprojeto.dto.validation.CheckCase;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;

    @NotNull(message = "O nome é obrigatório.")
    @NotEmpty(message = "O nome não pode ser vazio.")
    @CheckCase(value = CaseMode.UPPER, message = "Todas as letras devem ser maiúsculas")
    private String name;

    @NotNull(message = "E-mail é obrigatório.")
    @NotEmpty
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "Um formato valido de email é necessário.")
    @CheckCase(value = CaseMode.LOWER, message = "Não pode haver letras maiúsculas no email")
    private String email;
}
