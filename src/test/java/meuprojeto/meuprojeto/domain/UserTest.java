package meuprojeto.meuprojeto.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user;

    @Before
    public void setUp(){
        user = new User();
    }

    @Test
    public void getName() {
        String nameValue = "Thiago";
        user.setName(nameValue);

        assertEquals(nameValue, user.getName());
    }

    @Test
    public void getEmail() {
        String emailValue = "thiago@gmail.com";
        user.setEmail(emailValue);

        assertEquals(emailValue, user.getEmail());
    }
}