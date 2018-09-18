package meuprojeto.meuprojeto.repository;

import meuprojeto.meuprojeto.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class) //Ver a documentação
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @InjectMocks
    private User user;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void insertShouldPersistData(){

        user.setName("Thiago");
        user.setEmail("thiago@gmail.com");
        user.setId("23");

        this.userRepository.save(user);
        Optional<User> user1 = userRepository.findById("23");

        assertEquals(user.getName(), user1.get().getName());
        assertEquals(user.getEmail(), user1.get().getEmail());
        assertEquals(user.getId(), user1.get().getId());
    }

    @Test
    public void deleteShouldRemoveData(){

        user.setName("Thiago");
        user.setEmail("thiago@gmail.com");
        user.setId("23");

        this.userRepository.save(user);
        this.userRepository.deleteById("23");
        Optional<User> user1 = userRepository.findById("23");

        assertEquals(Optional.empty(), user1);
    }

    @Test
    public void updateSouldChangeAndPersistData(){

        user.setName("Thiago");
        user.setEmail("thiago@gmail.com");
        user.setId("23");

        this.userRepository.save(user);

        user.setName("Thiago123");
        user.setEmail("thiago123@gmail.com");

        this.userRepository.save(user);

        assertEquals("Thiago123", user.getName());
        assertEquals("thiago123@gmail.com", user.getEmail());
    }
}