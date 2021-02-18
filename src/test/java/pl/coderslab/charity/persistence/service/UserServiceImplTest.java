package pl.coderslab.charity.persistence.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import pl.coderslab.charity.persistence.dao.RoleRepository;
import pl.coderslab.charity.persistence.dao.UserRepository;
import pl.coderslab.charity.persistence.entity.Role;
import pl.coderslab.charity.persistence.entity.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void whenSearchingByEmail_ThenReturnObject() {
        //given
        User user1 = new User();
        user1.setEmail("test@gmail.com");
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");
        user1.setPassword("haslo");
        when(userRepository.findByEmail("test@gmail.com")).thenReturn(user1);
        //when
        User result = userService.findByEmail("test@gmail.com");
        //then
        assertEquals(user1.getEmail(), result.getEmail());
    }
    @Test
    public void whenSaveUser_ThenIsSaved() {
        //given
        User user1 = new User();
        user1.setEmail("test@gmail.com");
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");
        user1.setPassword("haslo");
        Role role = new Role();
        role.setName("ROLE_USER");
        user1.setRoles(new HashSet<Role>(Arrays.asList(role)));
        //when
        userService.registerNewUser(user1);
        //then
        assertNotEquals( "haslo", user1.getPassword());
    }
}