package kr.co.claveteam.Clava.application;

import kr.co.claveteam.Clava.domain.User;
import kr.co.claveteam.Clava.domain.UserRepository;
import kr.co.claveteam.Clava.parts.EmailNotExistedException;
import kr.co.claveteam.Clava.parts.PasswordWrongException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.initMocks(this);
       // userService = new UserService(userRepository, passwordEncoder);
    }
/*==
    @Test
    public void getUsers(){
        List<User> mockUsers = new ArrayList<User>();
        mockUsers.add(User.builder()
                .email("tester@example.com")
                .name("tester")
                .level(100L)
                .build());

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();

        User user = users.get(0);

        assertThat(user.getName()).isEqualTo("tester");
    }

    @Test
    public void addUser(){

        String email = "admin@example.com";
        String password = "test"
        String name = "Administrator";
        String
        String password = "test";
        String gender = "male";
        String organization = "school";
        String photourl = "aaa.com";

        User mockUser = User.builder()
                .nickname(nickname)
                .email(email)
                .password(encodedPassword)
                .name(name)
                .user_gender(gender)
                .user_organization(organization)
                .profilePhoto(photourl)
                .join_Date(ZonedDateTime.now())
                .level(1L)
                .build();
        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(email, password, name,);

        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    public void updateUser(){
        Long id = 1004L;
        String email = "admin@example.com";
        String name = "Superman";
        Long level = 100L;


        User mockUser = User.builder()
                .id(id)
                .email(email)
                .name("Administrator")
                .level(1L)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id,email,name,level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName()).isEqualTo("Superman");

        assertThat(user.isAdmin()).isEqualTo(true);
    }

    @Test
    public void deactiveUser(){
        Long id = 1004L;

        User mockUser = User.builder()
                .id(id)
                .email("admin@example.com")
                .name("Administrator")
                .level(100L)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deactiveUser(1004L);

        verify(userRepository).findById(1004L);

        assertThat(user.isAdmin()).isEqualTo(false) ;
        assertThat(user.isActive()).isEqualTo(false);
    }*/

    @Test
    public void authenticateWithValidAttributes(){

        String email = "admin@example.com";
        String password = "test";
        String name = "Administrator";
        String nickname = "tester";
        String user_gender = "male";
        String user_organization = "school";
        String photourl = "aaa.com";


        User mockUser = User.builder()
                .email(email)
                .build();

        given(userRepository.findByEmail(email))
                .willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(),any()))
                .willReturn(true);

        User user = userService.authenticate(email, password);

        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void authenticateWithNotExistedEmail(){

        String email = "x@example.com";
        String password = "test";

        EmailNotExistedException message =
                assertThrows(EmailNotExistedException.class, () -> {
                    userService.authenticate("x@example.com","test");
                });

        assertEquals(message.getMessage(), "등록된 이메일이 아닙니다.");
    }

    @Test
    public void authenticateWithWrongPassword(){

        String email = "tester@example.com";
        String password = "x";

        User mockUser = User.builder()
                .email(email)
                .build();

        given(userRepository.findByEmail(email))
                .willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(),any()))
                .willReturn(false);

        PasswordWrongException message =
                assertThrows(PasswordWrongException.class, () -> {
                    userService.authenticate("tester@example.com","x");
                });

        assertEquals(message.getMessage(), "비밀번호가 틀렸습니다.");

    }
}