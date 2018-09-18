package meuprojeto.meuprojeto.config;

import meuprojeto.meuprojeto.domain.Post;
import meuprojeto.meuprojeto.domain.User;
import meuprojeto.meuprojeto.dto.AuthorDTO;
import meuprojeto.meuprojeto.dto.CommentDTO;
import meuprojeto.meuprojeto.mapper.UserMapper;
import meuprojeto.meuprojeto.repository.PostRepository;
import meuprojeto.meuprojeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = User.builder().id(null).name("Maria Silva").email("maria@gmail.com").build();
        User alex = User.builder().id(null).name("Alex Silva").email("alex@gmail.com").build();
        User roberto = User.builder().id(null).name("Roberto Silveira").email("roberto@gmail.com").build();

        userRepository.saveAll(Arrays.asList(maria, alex, roberto));

        Post post1 = Post.builder()
                .id(null)
                .date(LocalDate.of(2018,3,21))
                .title("Partiu viagem!")
                .body("Vou viajar para São Paulo.")
                .author(UserMapper.mapUserToAuthorDto(maria))
                .build();
        Post post2 = Post.builder()
                .id(null)
                .date(LocalDate.of(2018,3,23))
                .title("Bom dia!")
                .body("Acordei feliz hoje.")
                .author(UserMapper.mapUserToAuthorDto(maria)).build();

        CommentDTO c1 = CommentDTO.builder()
                .text("Boa viagem mano!")
                .date(LocalDate.of(2018,3,21))
                .author(UserMapper.mapUserToAuthorDto(alex))
                .build();
        CommentDTO c2 = CommentDTO.builder()
                .text("Aproveite!")
                .date(LocalDate.of(2018,3,22))
                .author(UserMapper.mapUserToAuthorDto(roberto))
                .build();
        CommentDTO c3 = CommentDTO.builder()
                .text("Tenha um ótimo dia!!")
                .date(LocalDate.of(2018,3,23))
                .author(UserMapper.mapUserToAuthorDto(alex))
                .build();

        post1.setComments(Arrays.asList(c1, c2));
        post2.setComments(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.setPosts(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}