package meuprojeto.meuprojeto.services;

import meuprojeto.meuprojeto.domain.User;
import meuprojeto.meuprojeto.repository.UserRepository;
import meuprojeto.meuprojeto.controllers.exception.CustomDefaultException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {

        List<User> usersData = new ArrayList<>();
        usersData.add(user);

        when(userRepository.findAll()).thenReturn(usersData);

        List<User> users = userService.findAll();

        assertEquals(1, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void findById() {

        user.setName("Thiago");
        user.setEmail("thiago@gmail.com");
        user.setId("123");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User user1 = userService.findById(user.getId());

        assertEquals( "Thiago", user1.getName());
        assertEquals("thiago@gmail.com", user1.getEmail());
        assertEquals("123", user1.getId());
    }

    @Test(expected = CustomDefaultException.class)
    public void findByIdShouldThrowCustomDefaultExceptionIfUserDoesNotExists() {

        when(userRepository.findById("123")).thenReturn(Optional.empty());

        userService.findById("123");
    }

    @Test
    public void update() {

        user.setName("Thiago");
        user.setEmail("thiago@gmail.com");
        user.setId("123");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(anyObject())).thenReturn(user);

        User user1 = User.builder()
                .name("Thiago123")
                .email("thiago123@gmail.com")
                .id("123")
                .build();

        User user2 = this.userService.update(user1);

        assertEquals(user1, user2);
    }
}