package kr.co.claveteam.Clava.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.claveteam.Clava.application.CirclePostsService;
import kr.co.claveteam.Clava.domain.CirclePosts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CirclePostsController.class)
class CirclePostsControllerTest {

   @Autowired
    private MockMvc mvc;

   @Autowired
    ObjectMapper objectMapper;

   @MockBean
    private CirclePostsService circlePostsService;

   @Test
    public void creat() throws Exception{

       String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEyLCJuaWNrTmFtZSI6Iuu5hOy8nOuztOyEuOyalCJ9.5afUc4CpMODOtVHQwE7CklwSrvSBekUfX-4017E6B1E";

       List<String> temp = new ArrayList<>();
       temp.add("aaaaa");
       given(circlePostsService.addPost(10L, "비켜보세요","안녕하세요", temp))
               .willReturn(CirclePosts.builder().id(1004L).build());

       mvc.perform(post("/circles/10/posts")
               .header("Authorization", "Bearer " + token)
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"description\":\"안녕하세요\",\"photoUrl\":[\"aaaaa\"]}")
               //.content(objectMapper.writeValueAsString(temp))
       )
               .andExpect(status().isCreated())
               .andExpect(header().string("location", "/circles/10/posts/1004"));

       verify(circlePostsService).addPost(eq(10L), eq("비켜보세요"), eq("안녕하세요"), eq(temp));
   }

    @Test
    public void createWithInvalidAttriutes() throws Exception {
        mvc.perform(post("/circles/10/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(circlePostsService, never()).addPost(any(), any(), any(), any());
    }

}