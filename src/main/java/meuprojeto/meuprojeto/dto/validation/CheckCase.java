package meuprojeto.meuprojeto.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

//Anotação para verificar se o campo está completamente com letras minúsculas ou maiúsculas

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE}) // Em qual contexto a anotação pode ser usada
@Retention(RetentionPolicy.RUNTIME) // Define que o tipo anotado estará disponivel durante o tempo de execução
@Constraint(validatedBy = CheckCaseValidator.class) // Marca a classe como uma restrição de validação de beans e diz qual classe será utilizada para fazer a validação
@Documented // É uma anotação marcadora usada para indicar que os tipos anotação anotados com ela serão incluídos na documentação Javadoc
public @interface CheckCase {

    String message() default "{meuprojeto.meuprojeto.dto.validation.CheckCase.message}"; // Cria mensagem de erro caso a restrição seja violada

    Class<?>[] groups() default { }; // Para especificar o grupo de validações ao qual a restrição pertence

    Class<? extends Payload>[] payload() default { }; // Carrega metadados (dados sobre os dados)

    CaseMode value(); // Define se será verificado maiúsculas ou minúsculas

    @Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {         // Serve para poder usar diversas vezes a anotação.
        CheckCase[] value();
    }
}
