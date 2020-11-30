package kr.co.claveteam.Clava.interfaces;

import kr.co.claveteam.Clava.application.UserService;
import kr.co.claveteam.Clava.domain.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class CusUserController {

   //private User user;

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {


        String nickname = resource.getNickname();
        String email = resource.getEmail();
        String password = resource.getPassword();
        String name = resource.getName();
        String gender = resource.getUser_gender();
        String organization = resource.getUser_organization();
        String profilePhoto = resource.getProfilePhoto();

        User user = userService.registerUser(nickname,email,password,name,gender,organization,profilePhoto);

        String url = "/users/" + user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
