package kr.co.claveteam.Clava.interfaces;

import kr.co.claveteam.Clava.application.UserService;

import kr.co.claveteam.Clava.domain.User;
import kr.co.claveteam.Clava.parts.EmailNotExistedException;
import kr.co.claveteam.Clava.parts.PasswordWrongException;
import kr.co.claveteam.Clava.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void createWithVaildAttributes() throws Exception {
        Long id = 1004L;
        String name = "John";
        String email = "tester@example.com";
        String password = "test";

        User mockUser = User.builder().id(id).name(name).build();

        given(userService.authenticate(email,password)).willReturn(mockUser);

        given(jwtUtil.createToken(id,name)).willReturn("header.payload.signature");

        mvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"test\"," +
                        "\"nickname\":\"tester\",\"name\":\"tester1\"," +
                        "\"user_gender\":\"male\",\"user_organization\":\"school\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/auth"))
                .andExpect(content().string(
                        containsString("{\"accessToken\":\"header.payload.signature\"}")
                ));

        verify(userService).authenticate(eq(email), eq(password));
    }


    @Test
    public void createWithNotExistedEmail() throws Exception {

        given(userService.authenticate("x@example.com", "test"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"x@example.com\",\"password\":\"test\"," +
                        "\"nickname\":\"tester\",\"name\":\"tester1\"," +
                        "\"user_gender\":\"male\",\"user_organization\":\"school\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("x@example.com"), eq("test"));
    }

    @Test
    public void createWithWrongPassword() throws Exception {

        given(userService.authenticate("tester@example.com", "x"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"x\"," +
                        "\"nickname\":\"tester\",\"name\":\"tester1\"," +
                        "\"user_gender\":\"male\",\"user_organization\":\"school\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("tester@example.com"), eq("x"));
    }
}