package meuprojeto.meuprojeto.mapper;

import meuprojeto.meuprojeto.domain.User;
import meuprojeto.meuprojeto.dto.AuthorDTO;
import meuprojeto.meuprojeto.dto.UserDTO;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;

import java.util.Optional;

public class UserMapper {

    public static UserDTO mapDomainToDTO(User userDomain) {
        return Optional.ofNullable(userDomain)
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
//                .map(new Function<User, UserDTO>() {
//                    @Override
//                    public UserDTO apply(User user) {
//                        return UserDTO.builder()
//                        .id(user.getId())
//                        .name(user.getName())
//                        .email(user.getEmail())
//                        .build();
//                    }
//                })
                .orElse(null);
    }

    public static User mapDtoToDomain(UserDTO userDto) {
        return Optional.ofNullable(userDto)
                .map(user -> User.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .orElse(null);
    }

    public static AuthorDTO mapUserToAuthorDto(User userDomain){
        return Optional.ofNullable(userDomain)
                .map(user -> AuthorDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .build())
                .orElse(null);
    }
}