package kr.co.claveteam.Clava.interfaces;

import kr.co.claveteam.Clava.application.UserService;
import kr.co.claveteam.Clava.domain.User;
import org.apache.catalina.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.NotEmpty;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;
/*
    @Test
    public void list() throws Exception {

        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("tester@example.com")
                .name("tester")
                .level(100L)
                .build());

        given(userService.getUsers()).willReturn(users);
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tester")));
    }

    @Test
    public void create() throws Exception {
        String id = "Tester";
        String email = "admin@example.com";
        String name = "Administrator";
        String password = "test";
        String gender = "male";
        String organization = "school";
        String photourl = "aaa.com";

        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .user_gender(gender)
                .user_organization(organization)
                .profilePhoto(photourl)
                .join_Date(ZonedDateTime.now())
                .build();

        given(userService.addUser(id,email,password,name,gender,organization,photourl)).willReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@example.com\",\"name\":\"Administrator\"}"))
                .andExpect(status().isCreated());

        verify(userService).addUser(id,email,password,name,gender,organization,photourl);
    }

    @Test
    public void update() throws Exception {

        mvc.perform(patch("/users/Tester")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@example.com\"," +
                        "\"name\":\"Administrator\",\"level\":100}"))
                .andExpect(status().isOk());

        String id = "Tester";
        String email = "admin@example.com";
        String name = "Administrator";
        String password = "test";
        String gender = "male";
        String organization = "school";
        String photourl = "aaa.com";
        Long level = 100L;

        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .user_gender(gender)
                .user_organization(organization)
                .profilePhoto(photourl)
                .join_Date(ZonedDateTime.now())
                .build();
        id,email,password,name,gender,organization,photourl
        verify(userService).updateUser(eq(id), eq(email), eq(password), eq(name), eq(gender), eq(organization),
                ,eq(photourl), eq(level));
    }

    @Test
    public void deactivate() throws Exception {
        mvc.perform(delete("/users/Tester"))
                .andExpect(status().isOk());

        verify(userService).deactiveUser("Tester");
    }
*/
}