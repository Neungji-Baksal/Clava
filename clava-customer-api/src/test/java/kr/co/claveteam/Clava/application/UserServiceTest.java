package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.User;
import kr.co.claveteam.Clava.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }


    @Test
    public void registerUserWithExistedEmail() {

        String id="cnc";
        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";
        String gender = "male";
        String organization = "school";
        String profilePhoto = "aaaaa";

        EmailExistedException message =
                assertThrows(EmailExistedException.class, () -> {
                   userService.getEmail("tester@example.com");
                });
        assertEquals(message.getMessage(), "이미 등록된 이메일 입니다.");

        userService.registerUser(id,email,password,name,gender,organization,profilePhoto);
        verify(userRepository).save(any());
    }
}