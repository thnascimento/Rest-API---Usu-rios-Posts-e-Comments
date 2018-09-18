package meuprojeto.meuprojeto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data    //Cria gets, sets, hashcode e equals
@Builder //Otimiza a instanciação com .builder().build().
@NoArgsConstructor
@AllArgsConstructor
@Document  //Indica que é uma coleção MongoDB
public class User {

    @Id
    private String id;
    private String name;
    private String email;

    @DBRef(lazy = true) //(laze = true) para não carregar todos os posts quando recuperar um usuário do banco de dados
    private List<Post> posts;
}
